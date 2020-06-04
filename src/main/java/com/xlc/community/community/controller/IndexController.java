package com.xlc.community.community.controller;


import com.xlc.community.community.dto.PageDTO;
import com.xlc.community.community.dto.QuestionDTO;
import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.User;
import com.xlc.community.community.service.impl.QuestionDTOServiceImpl;
import com.xlc.community.community.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private QuestionDTOServiceImpl questionTDOService;

    @Autowired
    private QuestionServiceImpl questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request , Model model,
                        @RequestParam(value = "currentPage" ,defaultValue = "1")Integer currentPage
    ,                   @RequestParam(value = "pagSize" ,defaultValue = "2")Integer pagSize ) {
     //   从cookie 中获取tokene
        Cookie[] cookies = request.getCookies();
        String token = null;
        User user = null;
        if (  cookies != null && cookies.length > 0 ) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    Boolean key = redisTemplate.hasKey(token);
                    if (key == true){
                        user = (User) redisTemplate.opsForValue().get(token);
                        System.out.println("reids---------------------------------");
                    }else {
                        user = userMapper.findByTokne(token);
                    }
                    if (user != null) {
                        // 登录成功  session中 写入user 对象
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }

            }
        }
        PageDTO pageDTO = questionTDOService.findAll(currentPage,pagSize);

//        for (QuestionDTO questionDTO : list) {
//            questionDTO.setDescription("3223322");
//        }
        model.addAttribute("pageDTO",pageDTO);



        return "index";
    }

    }


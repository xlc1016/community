package com.xlc.community.community.controller;


import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
     //   从cookie 中获取token
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
        return "index";
    }

    }


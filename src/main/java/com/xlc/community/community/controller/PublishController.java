package com.xlc.community.community.controller;


import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.Question;
import com.xlc.community.community.model.User;
import com.xlc.community.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    private IQuestionService  questionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){

        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag" ,required = false) String tag,
                            HttpServletRequest request, Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title == null || title == ""){
            model.addAttribute("error","标题不能为空");
            return  "publish";
        }
        if (description == null || description == ""){
            model.addAttribute("error","问题补充不能为空");
            return  "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

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

        if (user == null){
            model.addAttribute("error","用户未登录");
            return  "publish";
        }
        question.setCreator(Integer.valueOf(user.getAccountId()));

        questionService.create(question);
        return "redirect:/";
    }
}

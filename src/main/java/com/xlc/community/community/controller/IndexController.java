package com.xlc.community.community.controller;


import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired(required = true)
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
     //   从cookie 中获取token
        Cookie[] cookies = request.getCookies();
        String token = null;
        User user = null;
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    user = userMapper.findByTokne(token);
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


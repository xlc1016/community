package com.xlc.community.community.interecption;


import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SesssionInterception implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

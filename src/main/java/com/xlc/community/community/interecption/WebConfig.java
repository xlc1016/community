package com.xlc.community.community.interecption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
* @author :xlc
* @date: 2020-6-8
* @description: 拦截器
*/
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    private  SesssionInterception sesssionInterception;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sesssionInterception).addPathPatterns("/**");
    }
}

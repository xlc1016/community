package com.xlc.community.community.controller;

import com.xlc.community.community.dto.AccessTokenDTO;
import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.User;
import com.xlc.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
* @author :xlc
* @date: 2020-3-19
* @description: github 的授权登陆
*/
@Controller
public class AuthorController {
    /**
    * @author :xlc
    * @date: 2020-3-19
    * @description: github  回调函数
    */
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${gitHub.client_id}")
    private String client_id;
    @Value("${gitHub.client_secret}")
    private String client_secret;
    @Value("${gitHub.redirect_uri}")
    private String redirect_uri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam (name= "code") String code
            , @RequestParam(name="state") String state , HttpServletRequest request, HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        // 获得token
        String token = gitHubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = null;
        // 获取github的user对象
        if (token != null) {
            githubUser = gitHubProvider.getUser(token);
        }
         // 判断对象 不为空登录成功
        if(githubUser != null){
            // 将数据存入数据库
            User user = new User();
            String token1 = UUID.randomUUID().toString();
            user.setToken(token1);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreate(new Date());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //将用户的token 存入cookie 中
            response.addCookie(new Cookie("token",token1));

            // 登录成功后重定向到index 页面
            return "redirect:/";


        }else{
            // 登录失败 重定向到index 页面

        }
        return "redirect:/";
    }

}

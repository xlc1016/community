package com.xlc.community.community.controller;

import com.xlc.community.community.dto.AccessTokenDTO;
import com.xlc.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/callback")
    public String callback(@RequestParam (name= "code") String code
            ,@RequestParam(name="state") String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8081/callback");
        accessTokenDTO.setClient_id("223876fd83b95e12ba7a");
        accessTokenDTO.setClient_secret("a67e75910e2485c516f6a8fc088e22f267cf7533");
        // 获得token
        String token = gitHubProvider.getAccessToken(accessTokenDTO);
        // 获取github的user对象
        GithubUser user = gitHubProvider.getUser(token);
       // System.out.println(user.getName());
        return "index";
    }

}

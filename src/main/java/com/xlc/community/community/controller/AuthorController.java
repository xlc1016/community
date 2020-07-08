package com.xlc.community.community.controller;

import com.xlc.community.community.dto.AccessTokenDTO;
import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.User;
import com.xlc.community.community.model.UserExample;
import com.xlc.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code
            , @RequestParam(name = "state") String state, HttpServletRequest request, HttpServletResponse response) {

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

        if (githubUser != null) {
            String token1 = null;

            if (githubUser.getId() != null) {
                // 先查询数据库中是否存入改用户信息
                UserExample userExample = new UserExample();
                userExample.createCriteria().andAccountidEqualTo(Long.toString(githubUser.getId()));
                List<User> users = userMapper.selectByExample(userExample);

                if (users.size() != 0) {
                   token1 = users.get(0).getToken();
                   User updateUser = new User();
                   updateUser.setGmtmodified(new Date());
                   updateUser.setName(users.get(0).getName());
                   updateUser.setToken(users.get(0).getToken());
                   updateUser.setAvatarurl(users.get(0).getAvatarurl());
                   UserExample userExample1 = new UserExample();
                   userExample1.createCriteria().andIdEqualTo(users.get(0).getId());
                   userMapper.updateByExampleSelective(updateUser,userExample1);
                } else {
                    // 将数据存入数据库
                    User user  = new User();
                    token1 = UUID.randomUUID().toString();
                    user.setToken(token1);
                    user.setAccountid(Long.toString(githubUser.getId()));
                    user.setName(githubUser.getName());
                    user.setGmtcreate(new Date());
                    user.setGmtmodified(user.getGmtcreate());
                    user.setAvatarurl(githubUser.getAvatarUrl());
                    userMapper.insert(user);
                    // 同时将用户信息存入到redis 中
                    ValueOperations valueOperations = redisTemplate.opsForValue();
                    valueOperations.set(user.getToken(), user);

                }
            }
            //将用户的token 存入cookie 中
            response.addCookie(new Cookie("token", token1));

            // 登录成功后重定向到index 页面
            return "redirect:/";


        } else {
            // 登录失败 重定向到index 页面

        }
        return "redirect:/";
    }
    
    /**
    * @author :xlc
    * @date: 2020-6-9
    * @description: 退出登录
    */
  @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
    // 删除服务器端的session
      request.getSession().removeAttribute("user");
      // 删除cookie
      Cookie cookie = new Cookie("token",null);
      cookie.setMaxAge(0);
      response.addCookie(cookie);
        return "redirect:/";
    }

}

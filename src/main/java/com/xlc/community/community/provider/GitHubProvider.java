package com.xlc.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.xlc.community.community.controller.GithubUser;
import com.xlc.community.community.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
/**
* @author :xlc
* @date: 2020-3-19
* @description: @Component  注解 不和controller 类似 但是不需要初始化到spring的上下文了
 * 调用的时候不用new 对象 注入使用
*/
@Component
public class GitHubProvider {

     

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        /**
        * @author :xlc
        * @date: 2020-3-19
        * @description: okhttp 的post 请求    获得token
         */
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        // fastJson 将对象转换为字符串
        String json= JSON.toJSONString(accessTokenDTO);
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string =  response.body().string();
            if(string != null) {
                String[] split = string.split("=");
                String token = split[1];
                String[] tokenStr = token.split("&");
                return tokenStr[0];
            }else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
    /**
    * @author :xlc
    * @date: 2020-5-20
    * @description:  获得 gitHub的信息
    */

    public GithubUser  getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            // jsonfast 将字json符串转换为对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return  githubUser;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}

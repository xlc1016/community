package com.xlc.community.community.cache;


import com.xlc.community.community.dto.TagTDO;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagCaChe {


    /**
     *@创建人 xlc
     *@创建时间 2020-7-30
     *@描述 所以的tag
     **/
    public static List<TagTDO> getTag(){
        List<TagTDO> list = new ArrayList<>();


        TagTDO program = new TagTDO();
        program.setCategoryName("开放语言");
        program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"
        ));
        list.add(program);

        TagTDO framework  = new TagTDO();
        framework .setCategoryName("平台框架");
        framework .setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        list.add(framework );


        TagTDO server   = new TagTDO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList( "linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        list.add(server );

        TagTDO tool   = new TagTDO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        list.add(tool );

        return  list;
    }


    /**
     *@创建人 xlc
     *@创建时间 2020-7-30
     *@描述 将所有的tags 整合一起
     **/
    public static List<String> getAllTags(){

        List<String> list  = new ArrayList<>();

        List<TagTDO> TagTDOS = getTag();
        for (TagTDO tagTDO : TagTDOS) {
            for(String tag : tagTDO.getTags()){
                list.add(tag);

            }

        }


        return  list;
    }
    /**
     *@创建人 xlc
     *@创建时间 2020-7-30
     *@描述  非法标签判断
     **/
    public static boolean isFeiFaTag(String tags){
        boolean flag = true;
        List<String> allTags = TagCaChe.getAllTags();
        String[] receiveTags = tags.split(",");

        for (String receiveTag : receiveTags) {
            for(String tages : allTags){
                flag = allTags.contains(receiveTag);
                if (flag){
                    break;
                }
            }
        }
        return flag;
    }


}

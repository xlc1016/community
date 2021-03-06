package com.xlc.community.community.controller;


import com.xlc.community.community.dto.NotificationDTO;
import com.xlc.community.community.dto.PageDTO;
import com.xlc.community.community.model.Notification;
import com.xlc.community.community.model.User;
import com.xlc.community.community.service.impl.NotificationTDOServiceImpl;
import com.xlc.community.community.service.impl.QuestionDTOServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionDTOServiceImpl questionTDOService;

    @Autowired
    private NotificationTDOServiceImpl notificationTDOService;
    /**
    * @author :xlc
    * @date: 2020-6-5
    * @description: profile/{action} action 传递的是可变参数
    */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action  , HttpServletRequest request
     ,Model model,@RequestParam(value = "currentPage" ,defaultValue = "1")Integer currentPage
            ,                   @RequestParam(value = "pageSize" ,defaultValue = "2")Integer pageSize){
// 从session中获取User
        User user =(User) request.getSession().getAttribute("user");

        if (user == null){
            return "redirect:/";
        }
        if ("questions".equals(action)){
            model.addAttribute("selection","questions");
            model.addAttribute("selectionName","我的问题");
            PageDTO pageDTO = questionTDOService.listByUser(user.getAccountid(), currentPage, pageSize);
            model.addAttribute("PropageDTO",pageDTO);
        }else if("replacs".equals(action)){
            model.addAttribute("selection","replacs");
            model.addAttribute("selectionName","最新回复");
           PageDTO<NotificationDTO> pageDTO  = notificationTDOService.listByUser(user.getAccountid(), currentPage, pageSize);
            model.addAttribute("PropageDTO",pageDTO);
            // 获取未读数
          //  int  unReadNum = notificationTDOService.countByUserForUNRead(Integer.parseInt(user.getAccountid()));
            int unReadNum  = (int) request.getSession().getAttribute("unRead");
            model.addAttribute("unReadNum",unReadNum);
        }

        return  "profile";
    }
}

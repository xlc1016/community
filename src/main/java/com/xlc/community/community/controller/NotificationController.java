package com.xlc.community.community.controller;


import com.xlc.community.community.dto.NotificationDTO;
import com.xlc.community.community.enums.NotificationTypeEnum;
import com.xlc.community.community.mapper.NotificationMapper;
import com.xlc.community.community.model.NotificationExample;
import com.xlc.community.community.model.User;
import com.xlc.community.community.service.INotificationTDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {


    @Autowired
    private INotificationTDOService notificationTDOService;


    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request, @PathVariable(name = "id" )Integer id){

        User user = (User) request.getSession().getAttribute("user");
        if (StringUtils.isEmpty(user)){
            return "redirect:/";
        }

         NotificationDTO notificationDTO = notificationTDOService.read(id, user);


        if (NotificationTypeEnum.REPALY_COMMONT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPALY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }



    }
}

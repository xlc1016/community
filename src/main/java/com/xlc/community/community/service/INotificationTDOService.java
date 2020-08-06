package com.xlc.community.community.service;


import com.xlc.community.community.dto.NotificationDTO;
import com.xlc.community.community.dto.PageDTO;
import com.xlc.community.community.model.Notification;
import com.xlc.community.community.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface INotificationTDOService {

    PageDTO<NotificationDTO> listByUser(String accountid, Integer currentPage, Integer pageSize);

    int countByUser(Integer creator);

    List<Notification>   pageListByUser(@Param("notifier") Integer notifier, @Param("currentPage") Integer currentPage,
                                                      @Param("pageSize") Integer pageSize);

    int countByUserForUNRead(Integer creator);
    // 修改通知的状态

    NotificationDTO read(Integer id, User user);

}

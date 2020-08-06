package com.xlc.community.community.service.impl;

import com.xlc.community.community.dto.NotificationDTO;
import com.xlc.community.community.dto.PageDTO;
import com.xlc.community.community.dto.QuestionDTO;
import com.xlc.community.community.enums.NotificationStatusEnum;
import com.xlc.community.community.enums.NotificationTypeEnum;
import com.xlc.community.community.exception.CustomizeErrorCode;
import com.xlc.community.community.exception.CustomizeExecption;
import com.xlc.community.community.mapper.NotificationMapper;
import com.xlc.community.community.model.Notification;
import com.xlc.community.community.model.NotificationExample;
import com.xlc.community.community.model.Question;
import com.xlc.community.community.model.User;
import com.xlc.community.community.service.INotificationTDOService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class NotificationTDOServiceImpl implements INotificationTDOService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private INotificationTDOService notificationTDOService;

    @Override
    public PageDTO<NotificationDTO> listByUser(String accountId, Integer currentPage, Integer pageSize) {

        PageDTO pageDTO = new PageDTO();

        Integer creator =  Integer.valueOf(accountId);

        int count = notificationTDOService.countByUser(creator); // 总页数
        // 防止手动传页面
        if (currentPage < 1){
            currentPage = 1;
        }
        pageDTO.setPage(count,pageSize,currentPage);

        if(currentPage >= pageDTO.getTotalPage() ){
            currentPage = pageDTO.getTotalPage();
        }
        int page = pageSize*(currentPage -1 );
       List<Notification> notifications = notificationTDOService.pageListByUser(creator, currentPage, pageSize);

       if (notifications.size()== 0){
           return pageDTO;
       }

        List<NotificationDTO> notificationDTOSList = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO  = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOSList.add(notificationDTO);
        }
        pageDTO.setList(notificationDTOSList);
        return pageDTO;

    }

// 获取总页数
    @Override
    public int countByUser(Integer creator) {

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andNotifierEqualTo(creator);

        Long num  =  notificationMapper.countByExample(notificationExample);
        int count = num.intValue();
        return count;
    }
    // 相同用户的所有回复记录
    @Override
    public List<Notification> pageListByUser(Integer outerid, Integer currentPage, Integer pageSize) {

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andNotifierEqualTo(outerid);
        notificationExample.setOrderByClause("gmt_create desc");
         List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(notificationExample, new RowBounds(currentPage, pageSize));
        return notifications;
    }

    @Override
    public int countByUserForUNRead(Integer creator) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andNotifierEqualTo(creator).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        Long num  =  notificationMapper.countByExample(notificationExample);
        int count = num.intValue();
        return count;
    }

    // 更新通知状态
    @Override
    public NotificationDTO read(Integer id, User user) {
         Notification notification = notificationMapper.selectByPrimaryKey(id);
         if (StringUtils.isEmpty(notification)){
            throw new CustomizeExecption(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
         }

        if (Objects.equals(notification.getReceiver(), user.getAccountid())) {
            throw new CustomizeExecption(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
         notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;

    }
}

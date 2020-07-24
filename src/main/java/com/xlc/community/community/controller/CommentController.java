package com.xlc.community.community.controller;


import com.xlc.community.community.dto.CommentDTO;
import com.xlc.community.community.dto.ResultDTO;
import com.xlc.community.community.exception.CustomizeErrorCode;
import com.xlc.community.community.model.Comment;
import com.xlc.community.community.model.User;
import com.xlc.community.community.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *@创建人 xlc
 *@创建时间 2020-7-9
 *@描述 回复的controller
 **/
@Controller
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object postComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user =(User) request.getSession().getAttribute("user");
        if (StringUtils.isEmpty(user)){
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(Integer.parseInt(user.getAccountid()));
        comment.setLikeCount(0);
        commentService.create(comment);
        return ResultDTO.okOf();

    }

}

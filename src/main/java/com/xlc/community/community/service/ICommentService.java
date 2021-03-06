package com.xlc.community.community.service;

import com.xlc.community.community.dto.CommentDTO;
import com.xlc.community.community.enums.CommentTypeEnum;
import com.xlc.community.community.model.Comment;
import com.xlc.community.community.model.User;

import java.util.List;

public interface ICommentService {

    // 新增
    void create(Comment comment , User user);

    List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum question);
}

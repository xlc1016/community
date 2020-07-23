package com.xlc.community.community.service.impl;

import com.xlc.community.community.enums.CommentTypeEnum;
import com.xlc.community.community.exception.CustomizeErrorCode;
import com.xlc.community.community.exception.CustomizeExecption;
import com.xlc.community.community.mapper.CommentMapper;
import com.xlc.community.community.mapper.QuestionMapper;
import com.xlc.community.community.model.Comment;
import com.xlc.community.community.model.Question;
import com.xlc.community.community.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *@创建人 xlc
 *@创建时间 2020-7-9
 *@描述 comment service
 **/
@Service
public class CommentServiceImpl implements ICommentService {

    /**
     *@创建人 xlc
     *@创建时间 2020-7-9
     *@描述 新增
     **/

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
  @Override
    public void create(Comment comment) {

        if (StringUtils.isEmpty(comment.getParentId())) {
            // 父类id 为空的时候抛出异常
            throw new CustomizeExecption(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if(comment.getType()== null || CommentTypeEnum.isExist(comment.getType())){

            throw  new CustomizeExecption(CustomizeErrorCode.TYPE_PARAM_WRONG);

        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()){
            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (StringUtils.isEmpty(dbComment)){
                throw new  CustomizeExecption(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            int i = commentMapper.insert(comment);
        }else{
           // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (StringUtils.isEmpty(question)){
                throw new  CustomizeExecption(CustomizeErrorCode.QUSTION_NOT_FOUND);
            }
            int i = commentMapper.insert(comment);
        }



    }
}

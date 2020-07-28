package com.xlc.community.community.service.impl;

import com.xlc.community.community.dto.CommentDTO;
import com.xlc.community.community.enums.CommentTypeEnum;
import com.xlc.community.community.exception.CustomizeErrorCode;
import com.xlc.community.community.exception.CustomizeExecption;
import com.xlc.community.community.mapper.CommentMapper;
import com.xlc.community.community.mapper.QuestionExtMapper;
import com.xlc.community.community.mapper.QuestionMapper;
import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.*;
import com.xlc.community.community.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void create(Comment comment) {

        if (StringUtils.isEmpty(comment.getParentId())) {
            // 父类id 为空的时候抛出异常
            throw new CustomizeExecption(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if(comment.getType()== null || !CommentTypeEnum.isExist(comment.getType())){

            throw  new CustomizeExecption(CustomizeErrorCode.TYPE_PARAM_WRONG);

        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()){
            // 回复评论
//            CommentExample commentExample = new CommentExample();
//            commentExample.createCriteria().andParentIdEqualTo(comment.getParentId());
//            List<Comment> dbComment = commentMapper.selectByExample(commentExample);
//            if (StringUtils.isEmpty(dbComment)){
//                throw new  CustomizeExecption(CustomizeErrorCode.COMMENT_NOT_FOUND);
//            }
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            question.setCommentCount(1);
            questionExtMapper.incComment(question);
            int i = commentMapper.insert(comment);
        }else{
           // 回复问题
             Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentId());
             Question question = questionMapper.selectByPrimaryKey(comment1.getParentId());
            if (StringUtils.isEmpty(question)){
                throw new  CustomizeExecption(CustomizeErrorCode.QUSTION_NOT_FOUND);
            }
            int i = commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incComment(question);
        }



    }


    @Override
    public List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum type) {

        CommentExample commentExample  = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() == 0){
            return  new ArrayList<>();
        }
        // 获取去重人数
     //   Set<Integer> commentators  = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        Set<Integer> set = new HashSet<>();
        for (Comment comment : comments) {
            set.add(comment.getCommentator());
        }

        List<User> listUser  = new ArrayList<>();
        // 获取所以的user
        for (Integer  userId : set) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountidEqualTo(userId.toString());
            List<User> users = userMapper.selectByExample(userExample);
            listUser.add(users.get(0));
        }
        //
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            for (User user : listUser) {
                if (user.getAccountid().equals(comment.getCommentator().toString())){
                    commentDTO.setUser(user);
                }
            }
            commentDTOList.add(commentDTO);
        }

        return commentDTOList;
    }
}

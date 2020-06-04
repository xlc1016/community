package com.xlc.community.community.service.impl;

import com.xlc.community.community.dto.PageDTO;
import com.xlc.community.community.dto.QuestionDTO;
import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.Question;
import com.xlc.community.community.model.User;
import com.xlc.community.community.service.IQuestionService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionDTOServiceImpl
{

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private UserMapper userMapper;


    public PageDTO findAll(Integer currentPage,Integer pageSize ) {

        PageDTO pageDTO = new PageDTO();
        List<QuestionDTO> dtoList = new ArrayList<>();
        int count = questionService.count(); // 总页数
        // 反正手动传页面
        if (currentPage < 1){
            currentPage = 1;
        }
        pageDTO.setPage(count,pageSize,currentPage);

        if(currentPage >= pageDTO.getTotalPage() ){
            currentPage = pageDTO.getTotalPage();
        }

        int page = pageSize*(currentPage -1 );
        List<Question> list = questionService.pageList(page,pageSize);
        for (Question question : list) {
            User user = userMapper.findByAccountId2(Integer.toString(question.getCreator()));
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setUser(user);
            BeanUtils.copyProperties(question,questionDTO);
            dtoList.add(questionDTO);
        }
        pageDTO.setList(dtoList);


        return pageDTO;
    }
}

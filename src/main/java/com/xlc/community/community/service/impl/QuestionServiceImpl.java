package com.xlc.community.community.service.impl;

import com.xlc.community.community.mapper.QuestionMapper;
import com.xlc.community.community.model.Question;
import com.xlc.community.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl implements IQuestionService {


    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void create(Question question) {
        questionMapper.create(question);

    }
}

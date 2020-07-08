package com.xlc.community.community.service.impl;

import com.xlc.community.community.mapper.QuestionMapper;
import com.xlc.community.community.model.Question;
import com.xlc.community.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionServiceImpl implements IQuestionService {


    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void create(Question question) {
        questionMapper.create(question);

    }

    @Override
    public List<Question> findAll() {

        List<Question> list = questionMapper.findAll();

        return list;

    }

    @Override
    public List<Question> pageList(Integer currentPage, Integer pageSize) {

        List<Question> list = questionMapper.pageList(currentPage, pageSize);
        return list;
    }

    @Override
    public int count() {

        int count = questionMapper.count();
        return count;
    }

    @Override
    public int countByUser(Integer creator) {

        int count = questionMapper.countByUser(creator);
        return count;
    }

    @Override
    public List<Question> pageListByUser(Integer creator, Integer currentPage, Integer pageSize) {

        List<Question> list = questionMapper.pageListByUser(creator, currentPage, pageSize);
        return list;
    }

    @Override
    public Question findById(Integer id) {
        return questionMapper.findById(id);
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null){

            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
            // 新增
        }else{
            // 更新
           question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);

        }
    }
}

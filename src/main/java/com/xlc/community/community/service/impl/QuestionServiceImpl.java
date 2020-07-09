package com.xlc.community.community.service.impl;

import com.xlc.community.community.mapper.QuestionMapper;
import com.xlc.community.community.model.Question;
import com.xlc.community.community.model.QuestionExample;
import com.xlc.community.community.service.IQuestionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionServiceImpl implements IQuestionService {


    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void create(Question question) {
        int insert = questionMapper.insert(question);

    }

    @Override
    public List<Question> findAll() {


        List<Question> list = questionMapper.selectByExample(new QuestionExample());

        return list;

    }

    @Override
    public List<Question> pageList(Integer currentPage, Integer pageSize) {

        List<Question> list = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(currentPage,pageSize));
        return list;
    }

    @Override
    public int count() {
        QuestionExample questionExample = new QuestionExample();

            int count = (int) questionMapper.countByExample(questionExample);
        return count;
    }

    @Override
    public int countByUser(Integer creator) {

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(creator);
        int count = (int) questionMapper.countByExample(questionExample);
        return count;
    }

    @Override
    public List<Question> pageListByUser(Integer creator, Integer currentPage, Integer pageSize) {

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(creator);

        List<Question> list = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample,new RowBounds(currentPage,pageSize));
        return list;
    }

    @Override
    public Question findById(Integer id) {
        // 根据id查询
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
            // 新增
        }else{
            // 更新
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setGmtModified(System.currentTimeMillis());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            int i = questionMapper.updateByExampleSelective(updateQuestion, questionExample);

        }
    }
}

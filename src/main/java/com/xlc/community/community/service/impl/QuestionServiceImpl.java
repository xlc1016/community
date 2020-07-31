package com.xlc.community.community.service.impl;

import com.xlc.community.community.exception.CustomizeErrorCode;
import com.xlc.community.community.exception.CustomizeExecption;
import com.xlc.community.community.mapper.QuestionExtMapper;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

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

         QuestionExample example = new QuestionExample();
         example.setOrderByClause("gmt_create desc");
        List<Question> list = questionMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(currentPage,pageSize));
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
        questionExample.setOrderByClause(" gmt_create  desc");

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
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
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
   /**
    *@创建人 xlc
    *@创建时间 2020-7-9
    *@描述  更新浏览次数
    **/
    @Override
    public void updateViewCount(Integer id) {

        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}

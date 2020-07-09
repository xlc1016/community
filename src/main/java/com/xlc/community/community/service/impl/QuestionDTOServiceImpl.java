package com.xlc.community.community.service.impl;

import com.xlc.community.community.dto.PageDTO;
import com.xlc.community.community.dto.QuestionDTO;
import com.xlc.community.community.exception.CustomizeErrorCode;
import com.xlc.community.community.exception.CustomizeExecption;
import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.Question;
import com.xlc.community.community.model.User;
import com.xlc.community.community.model.UserExample;
import com.xlc.community.community.service.IQuestionService;
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
        int count = questionService.count(); // 总页数
        // 防止手动传页面
        if (currentPage < 1){
            currentPage = 1;
        }
        pageDTO.setPage(count,pageSize,currentPage);

        if(currentPage >= pageDTO.getTotalPage() ){
            currentPage = pageDTO.getTotalPage();
        }

        int page = pageSize*(currentPage -1 );
        if (page <= 0) {
            page = 0;
        }
        List<Question> list = questionService.pageList(page,pageSize);

        List<QuestionDTO> questionDTO = getQuestionDTO(list);
        pageDTO.setList(questionDTO);


        return pageDTO;
    }

    
    /**
    * @author :xlc
    * @date: 2020-6-5
    * @description: 根据用户名分页查询
    */
    public PageDTO listByUser(String accountId, Integer currentPage, Integer pageSize) {

        PageDTO pageDTO = new PageDTO();

         Integer creator =  Integer.valueOf(accountId);

        int count = questionService.countByUser(creator); // 总页数
        // 防止手动传页面
        if (currentPage < 1){
            currentPage = 1;
        }
        pageDTO.setPage(count,pageSize,currentPage);

        if(currentPage >= pageDTO.getTotalPage() ){
            currentPage = pageDTO.getTotalPage();
        }
        int page = pageSize*(currentPage -1 );
        List<Question> list = questionService.pageListByUser(creator,page,pageSize);

        List<QuestionDTO> questionDTO = getQuestionDTO(list);

        pageDTO.setList(questionDTO);

        return pageDTO;

    }

    private   List<QuestionDTO>  getQuestionDTO(List<Question> list){

        List<QuestionDTO> dtoList = new ArrayList<>();

        for (Question question : list) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountidEqualTo(Integer.toString(question.getCreator()));

            List<User> users = userMapper.selectByExample(userExample);

            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setUser(users.get(0));
            BeanUtils.copyProperties(question,questionDTO);
            dtoList.add(questionDTO);
        }

        return dtoList;

    }
    /**
    * @author :xlc
    * @date: 2020-6-9
    * @description:  根据id查询
    */

    public QuestionDTO findById(Integer id) {
        Question question = questionService.findById(id);
        if (question == null){
          throw  new CustomizeExecption(CustomizeErrorCode.QUSTION_NOT_FOUND);

        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountidEqualTo(Integer.toString(question.getCreator()));
        List<User> users = userMapper.selectByExample(userExample);
       // User user = userMapper.findByAccountId2(Integer.toString(question.getCreator()));
        questionDTO.setUser(users.get(0));
        return questionDTO;
    }
  /**
   *@创建人 xlc
   *@创建时间 2020-7-9
   *@描述 更新浏览次数
   **/
    public void updateViewCount(Integer id) {
        questionService.updateViewCount(id);
    }
}

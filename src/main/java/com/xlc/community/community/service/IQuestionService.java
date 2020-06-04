package com.xlc.community.community.service;

import com.xlc.community.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IQuestionService {

    public void create(Question question);

    List<Question> findAll();

    // 分页查询
    public  List<Question> pageList(@Param("currentPage") Integer currentPage,
                                    @Param("pageSize") Integer pageSize );
    // 查询共计多少条
    public int count();
}

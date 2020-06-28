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


    // 查询共计多少条（根据用户命）
    public int countByUser(@Param("creator") Integer creator );

    List<Question> pageListByUser(@Param("creator") Integer creator, @Param("currentPage") Integer currentPage,
                                  @Param("pageSize") Integer pageSize);
    // 根据id查询
    Question findById(@Param("id") Integer id);
}

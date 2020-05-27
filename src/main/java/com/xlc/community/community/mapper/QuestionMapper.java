package com.xlc.community.community.mapper;

import com.xlc.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface QuestionMapper {

     public void create(Question question);
}

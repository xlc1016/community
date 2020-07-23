package com.xlc.community.community.mapper;

import com.xlc.community.community.model.Question;
import com.xlc.community.community.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionExtMapper {

    void incView(Question question);

    void incComment(Question  question);
}
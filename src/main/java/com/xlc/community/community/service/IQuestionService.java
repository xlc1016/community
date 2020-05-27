package com.xlc.community.community.service;

import com.xlc.community.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

public interface IQuestionService {

    public void create(Question question);
}

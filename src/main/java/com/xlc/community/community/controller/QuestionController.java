package com.xlc.community.community.controller;

import com.xlc.community.community.dto.CommentDTO;
import com.xlc.community.community.dto.QuestionDTO;
import com.xlc.community.community.enums.CommentTypeEnum;
import com.xlc.community.community.service.ICommentService;
import com.xlc.community.community.service.impl.QuestionDTOServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionDTOServiceImpl questionTDOService;


    @Autowired
    private  ICommentService commentService;

    

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id  , Model model){

        QuestionDTO questionDTO = questionTDOService.findById(id);
        // 回复内容
        List<CommentDTO> commentDTOList = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);

        // 更新浏览次数
        questionTDOService.updateViewCount(id);

        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("commentDTO",commentDTOList);
        model.addAttribute("creator" ,Integer.toString(questionDTO.getCreator()));

        return "question";
    }
}

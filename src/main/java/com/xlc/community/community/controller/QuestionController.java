package com.xlc.community.community.controller;

import com.xlc.community.community.dto.PageDTO;
import com.xlc.community.community.dto.QuestionDTO;
import com.xlc.community.community.service.impl.QuestionDTOServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionDTOServiceImpl questionTDOService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id  , Model model){

        QuestionDTO questionDTO = questionTDOService.findById(id);

        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("creator" ,Integer.toString(questionDTO.getCreator()));
     //   model.addAttribute("accountId",questionDTO.getUser().getAccountId());


        return "question";
    }
}

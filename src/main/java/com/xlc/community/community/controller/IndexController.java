package com.xlc.community.community.controller;


import com.xlc.community.community.dto.PageDTO;
import com.xlc.community.community.service.impl.QuestionDTOServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {



    @Autowired(required = false)
    private QuestionDTOServiceImpl questionTDOService;

    @GetMapping("/")
    public String index( Model model,
                        @RequestParam(value = "currentPage" ,defaultValue = "1")Integer currentPage
    ,                   @RequestParam(value = "pagSize" ,defaultValue = "2")Integer pagSize ) {

        PageDTO pageDTO = questionTDOService.findAll(currentPage,pagSize);

        model.addAttribute("pageDTO",pageDTO);



        return "index";
    }

    }


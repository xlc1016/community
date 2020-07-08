package com.xlc.community.community.controller;


import com.xlc.community.community.model.Question;
import com.xlc.community.community.model.User;
import com.xlc.community.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private IQuestionService  questionService;


    @GetMapping("/publish")
    public String publish(){

        return "publish";
    }


    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id" ,required = false)Integer id, Model model){
        if(!StringUtils.isEmpty(id.toString())){
            Question question = questionService.findById(id);
            model.addAttribute("title",question.getTitle());
            model.addAttribute("description",question.getDescription());
            model.addAttribute("tag",question.getTag());
        }
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag" ,required = false) String tag,
                            HttpServletRequest request, Model model,@RequestParam(value = "id",required = false) Integer id){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title == null || title == ""){
            model.addAttribute("error","标题不能为空");
            return  "publish";
        }
        if (description == null || description == ""){
            model.addAttribute("error","问题补充不能为空");
            return  "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);


        User user =(User) request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error","用户未登录");
            return  "publish";
        }
        question.setCreator(Integer.valueOf(user.getAccountid()));
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}

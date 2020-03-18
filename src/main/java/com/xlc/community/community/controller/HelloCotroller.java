package com.xlc.community.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("hello")
public class HelloCotroller {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model mode) {
        mode.addAttribute("name", name);
        return "hello";
    }
}

package com.example.project_collatool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/view")
    public String loginPage(){
        return "login";
    }



}

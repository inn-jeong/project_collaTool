package com.example.project_collatool.controller;

import com.example.project_collatool.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/main")
public class MainController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/")
    public String rootMain(HttpSession session){
//        UserDto user = (UserDto) session.getAttribute("user");
//        if(user != null){
//            return "redirect:/view";
//        }
//        return "redirect:/b_login";
        return "redirect:/main/view";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/view")
    public String mainView(){
        return "main";
    }

    @RequestMapping("/b_login")
    public String beforeLogin(){
        return "before_login";
    }

}

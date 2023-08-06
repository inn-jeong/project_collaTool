package com.example.project_collatool.controller;

import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.UserDto;
import com.example.project_collatool.repository.ProjectRepository;
import com.example.project_collatool.service.MainService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;

    @PreAuthorize("is")
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
    @RequestMapping("/view")
    public String mainView(Principal principal, Model model) {
        List<ProjectDto> projectDtoList= mainService.findAll(principal.getName());
        model.addAttribute("projectList",projectDtoList);
        return "main";
    }

    @RequestMapping("/b_login")
    public String beforeLogin(){
        return "before_login";
    }

}

package com.example.project_collatool.controller;

import com.example.project_collatool.converter.ProjectConverter;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.repository.ProjectRepository;
import com.example.project_collatool.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/view/{projectId}")
    public String projectView(@PathVariable Integer projectId, Model model){
        ProjectDto project = projectService.findById(projectId);
//        LocalDate startDate = LocalDate.parse(project.getPCreated(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//        LocalDate endDate = LocalDate.parse(project.getPDeadline(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        model.addAttribute("project",project);
        return "project/project_view";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/createView")
    public String createViewProject(){
        return "project/project_create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/todoList")
    public String todoList(Model model){

        return "project/todoList";
    }

}

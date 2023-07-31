package com.example.project_collatool.controller;

import com.example.project_collatool.converter.ProjectConverter;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/project")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    @RequestMapping("/view/{projectId}")
    public String projectView(@PathVariable Integer projectId, Model model){

        return "project/project_view";
    }

    @RequestMapping("/createView")
    public String createViewProject(){
        return "project/project_create";
    }

}

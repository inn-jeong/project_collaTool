package com.example.project_collatool.controller;

import com.example.project_collatool.converter.ProjectConverter;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.repository.ProjectRepository;
import com.example.project_collatool.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/projectRest")
public class ProjectRestController {
    private final ProjectService projectService;
    @PostMapping("/create")
    public ResponseEntity<String> createProject(ProjectDto projectDto){
        log.info("@# project create ===> "+projectDto);
        projectService.insertProject(projectDto);
        return new ResponseEntity<String>("insert",HttpStatus.OK);
    }
}

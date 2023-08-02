package com.example.project_collatool.controller;

import com.example.project_collatool.converter.ProjectConverter;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.TodoListDto;
import com.example.project_collatool.repository.ProjectRepository;
import com.example.project_collatool.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/view/{projectId}")
    public String projectView(@PathVariable Integer projectId, HttpSession session, Model model){
        ProjectDto project = projectService.findById(projectId);
//        LocalDate startDate = LocalDate.parse(project.getPCreated(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//        LocalDate endDate = LocalDate.parse(project.getPDeadline(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        session.setAttribute("projectId",projectId);
        model.addAttribute("project",project);
        return "project/project_view";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/createView")
    public String createViewProject(){
        return "project/project_create";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/todoList")
    public String todoList(HttpSession session,Model model,Principal principal){
        String uId = principal.getName();
        Integer projectId = (Integer)session.getAttribute("projectId");
        log.info("@# todo name ===>" + uId);
        List<TodoListDto> todoList = projectService.findByUIdAndProjectId(uId,projectId);
        model.addAttribute("projectId",projectId);
        model.addAttribute("todoList",todoList);
        return "project/todoList";
    }


}

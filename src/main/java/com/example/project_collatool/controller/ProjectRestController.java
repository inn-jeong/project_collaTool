package com.example.project_collatool.controller;

import com.example.project_collatool.converter.ProjectConverter;
import com.example.project_collatool.db.TodoListEntity;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.TodoListDto;
import com.example.project_collatool.repository.ProjectRepository;
import com.example.project_collatool.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/projectRest")
public class ProjectRestController {
    private final ProjectService projectService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<String> createProject(ProjectDto projectDto){
        log.info("@# project create ===> "+projectDto);
        projectService.insertProject(projectDto);
        return new ResponseEntity<String>("insert",HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/insert-todo")
    public ResponseEntity<List<TodoListDto>> insertTodo(@RequestParam List<String> tdTitle, Principal principal, HttpSession session){
        String uId = principal.getName();
        Integer projectId = (Integer)session.getAttribute("projectId");
        log.info("@# insert todoList titles ===>"+tdTitle);
        projectService.saveTodoList(tdTitle,projectId,uId);

        List<TodoListDto> todoList = projectService.findByUIdAndProjectId(uId,projectId);
        log.info("@# insert todoList ===> "+todoList);
        return new ResponseEntity<>(todoList,HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/complete-todo")
    public ResponseEntity<List<TodoListDto>> completeTodo(@RequestParam List<Integer> tdId, Principal principal, HttpSession session){
        String uId = principal.getName();
        Integer projectId = (Integer)session.getAttribute("projectId");
        projectService.updateTodoList(tdId);
        List<TodoListDto> todoList = projectService.findByUIdAndProjectId(uId,projectId);
        return new ResponseEntity<>(todoList,HttpStatus.OK);
    }
}

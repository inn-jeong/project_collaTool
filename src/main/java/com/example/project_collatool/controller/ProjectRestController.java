package com.example.project_collatool.controller;

import com.example.project_collatool.dto.BoardDto;
import com.example.project_collatool.dto.CommentDto;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.TodoListDto;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/projectRest")
public class ProjectRestController {
    private final ProjectService projectService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<String> createProject(ProjectDto projectDto, Principal principal){
        log.info("@# project create ===> "+projectDto);
        projectService.insertProject(projectDto,principal.getName());
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board-create")
    public ResponseEntity<BoardDto> boardCreate(BoardDto boardDto){
        log.info("@# board create start===============");
        projectService.saveBoard(boardDto);
        log.info("@# board create select===============");
        BoardDto tempDto = projectService.selectBoard(boardDto.getBProjectId(),boardDto.getBUId());
        return new ResponseEntity<>(tempDto,HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board-modify")
    public ResponseEntity<BoardDto> boardModify(BoardDto boardDto){
        log.info("@# board create start===============");
        projectService.updateBoard(boardDto.getBId(),boardDto.getBTitle(),boardDto.getBContent());
        log.info("@# board create select===============");
        BoardDto tempDto = projectService.selectBoard(boardDto.getBProjectId(),boardDto.getBUId());
        return new ResponseEntity<>(tempDto,HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board-delete")
    public ResponseEntity<String> boardDelete(@RequestParam Integer bId){
        projectService.deleteBoard(bId);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board-paging")
    public ResponseEntity<List<BoardDto>> pagingBoard(@RequestParam int paging){
        List<BoardDto> list = new ArrayList<>();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addMember")
    public ResponseEntity<String> addMember(@RequestParam List<Integer> userId, HttpSession session){
        Integer projectId = (Integer)session.getAttribute("projectId");
        projectService.addMember(userId,projectId);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/insert-comment")
    public ResponseEntity<String> insertComment(CommentDto dto){
        projectService.insertComment(dto);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/select-comment")
    public ResponseEntity<List<CommentDto>> selelctComment(Integer bId){
        List<CommentDto> commentList = projectService.selectAllComment(bId);
        return new ResponseEntity<>(commentList,HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-comment")
    public ResponseEntity<String> deleteComment(@RequestParam Integer cId){
        projectService.deleteComment(cId);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
}

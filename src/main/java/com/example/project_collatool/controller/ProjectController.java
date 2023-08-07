package com.example.project_collatool.controller;

import com.example.project_collatool.dto.BoardDto;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.TodoListDto;
import com.example.project_collatool.dto.UserDto;
import com.example.project_collatool.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/view/{projectId}")
    public String projectView(@PathVariable Integer projectId, HttpSession session, Model model, Principal principal){
        ProjectDto project = projectService.findById(projectId);
        session.setAttribute("projectId",projectId);
        model.addAttribute("pMembers",projectService.findMembers(projectId));
        model.addAttribute("project",project);
        model.addAttribute("progress",showProgress(project));
        model.addAttribute("user", projectService.findUserByUid(principal.getName()));
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

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/baord")
    public String boardView(HttpSession session, Model model){
        Integer projectId = (Integer) session.getAttribute("projectId");
        List<BoardDto> boardList = projectService.selectAllBoard(projectId);
        model.addAttribute("boardList",boardList);
        return "project/workBoard";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/board-create")
    public String boardCreate(HttpSession session,Model model,Principal principal){
        String uId = principal.getName();
        Integer projectId = (Integer)session.getAttribute("projectId");
        model.addAttribute("bProjectId",projectId);
        model.addAttribute("bUId",uId);
        return "project/board_create";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/board-view/{bId}")
    public String boardView(@PathVariable Integer bId, Model model,Principal principal){
        model.addAttribute("uId",principal.getName());
        model.addAttribute("board",projectService.selectBoard(bId));
        return "project/board_view";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/board-modify/{bId}")
    public String boardModifyView(@PathVariable Integer bId, Model model){
        model.addAttribute("board",projectService.selectBoard(bId));
        return "project/board_modify";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/board-search")
    public String boardSearch(@RequestParam("keyword") String keyword, HttpSession session, Model model){
        Integer projectId = (Integer) session.getAttribute("projectId");
        List<BoardDto> boardList = projectService.selectBoardSearch(projectId,keyword);
        model.addAttribute("boardList",boardList);
        return "project/workBoard";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/addMember")
    public String addMember(HttpSession session,Model model,Principal principal){
        String uId = principal.getName();
        Integer projectId = (Integer)session.getAttribute("projectId");
        List<UserDto> memberList = projectService.selectMembers(projectId);
        model.addAttribute("memberList",memberList);

        return "project/addMember";
    }

    private int showProgress(ProjectDto project){
        LocalDate localStartDate = LocalDate.parse(project.getPCreated(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate localEndDate = LocalDate.parse(project.getPDeadline(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        long totalDuration = endDate.getTime() - startDate.getTime();
        long currentDuration = new Date().getTime() - startDate.getTime();
        int progress = (int) ((currentDuration * 100) / totalDuration);
        if(progress < 0) progress=0;
        return progress;
    }

}

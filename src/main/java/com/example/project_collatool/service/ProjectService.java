package com.example.project_collatool.service;

import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.TodoListDto;

import java.util.List;

public interface ProjectService {
    List<TodoListDto> findAllTodoList(Integer userId);
    void insertProject(ProjectDto projectDto);
}

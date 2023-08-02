package com.example.project_collatool.service;

import com.example.project_collatool.db.TodoListEntity;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.TodoListDto;

import java.util.List;

public interface ProjectService {
    void insertProject(ProjectDto projectDto);
    ProjectDto findById(Integer projectId);
    List<TodoListDto> findByUIdAndProjectId(String uId, Integer projectId);
    void saveTodoList(List<String> tdtitles, Integer projectId, String uId);
    void updateTodoList(List<Integer> uIds);
}

package com.example.project_collatool.service;


import com.example.project_collatool.converter.ProjectConverter;
import com.example.project_collatool.converter.TodoListConverter;
import com.example.project_collatool.db.ProjectEntity;
import com.example.project_collatool.db.TodoListEntity;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.TodoListDto;
import com.example.project_collatool.repository.ProjectRepository;
import com.example.project_collatool.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ProjectService")
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService{
    private final TodoListRepository todoListRepository;
    private final TodoListConverter todoListConverter;
    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    @Override
    public List<TodoListDto> findAllTodoList(Integer userId) {
        List<TodoListEntity> todoList = todoListRepository.findByUserId(userId);
        List<TodoListDto> todoDtoList = new ArrayList<>();
        for (TodoListEntity entity: todoList) {
            todoDtoList.add(todoListConverter.toDto(entity));
        }
        return todoDtoList;
    }

    @Override
    public void insertProject(ProjectDto projectDto) {
        projectDto.setPCreated(projectDto.getPCreated());
        projectRepository.save(projectConverter.toEntity(projectDto));
    }
}

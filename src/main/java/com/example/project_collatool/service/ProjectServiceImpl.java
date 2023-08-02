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
    public void insertProject(ProjectDto projectDto) {
        projectDto.setPCreated(projectDto.getPCreated());
        projectRepository.save(projectConverter.toEntity(projectDto));
    }

    @Override
    public ProjectDto findById(Integer projectId) {
        return projectConverter.toDto(projectRepository.findById(projectId).get());
    }

    @Override
    public List<TodoListDto> findByUIdAndProjectId(String uId, Integer projectId) {
        List<TodoListEntity> todoEntityList = todoListRepository.findByuIdAndProjectId(uId,projectId);
        List<TodoListDto> todoDtoList = new ArrayList<>();
        for (TodoListEntity entity: todoEntityList) {
            todoDtoList.add(todoListConverter.toDto(entity));
        }
        return todoDtoList;
    }

    @Override
    public void saveTodoList(List<String> tdtitles, Integer projectId, String uId) {
        for (String title:tdtitles) {
            if(!title.equals("")){
                TodoListEntity entity = TodoListEntity.builder()
                        .projectId(projectId)
                        .uId(uId)
                        .tdTitle(title)
                        .tdCheck(0)
                        .build();
                todoListRepository.save(entity);
            }
        }
    }

    @Override
    public void updateTodoList(List<Integer> uIds) {
        for(Integer uId:uIds){
            todoListRepository.updateTodoList(uId);
        }
    }
}

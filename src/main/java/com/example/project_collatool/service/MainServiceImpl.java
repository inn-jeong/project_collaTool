package com.example.project_collatool.service;

import com.example.project_collatool.converter.ProjectConverter;
import com.example.project_collatool.db.ProjectEntity;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("MainService")
@RequiredArgsConstructor
@Slf4j
public class MainServiceImpl implements MainService{

    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;
    @Override
    public List<ProjectDto> findAll() {
        List<ProjectEntity> projectEntityList = projectRepository.findAll();
        List<ProjectDto> projectDtoList = new ArrayList<>();

        for (ProjectEntity entity: projectEntityList) {
            projectDtoList.add(projectConverter.toDto(entity));
        }
        return projectDtoList;
    }
}

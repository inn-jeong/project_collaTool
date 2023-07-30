package com.example.project_collatool.converter;

import com.example.project_collatool.db.ProjectEntity;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectConverter implements Converter<ProjectEntity, ProjectDto> {
    private final ProjectRepository projectRepository;
    @Override
    public ProjectEntity toEntity(ProjectDto projectDto) {
        Optional<ProjectEntity> byId = projectRepository.findById(projectDto.getProjectId());

        ProjectEntity entity = ProjectEntity.builder()
                .projectId(byId.get().getProjectId())
                .pName(projectDto.getPName())
                .pCategory(projectDto.getPCategory())
                .pExplain(projectDto.getPExplain())
                .pCreated(projectDto.getPCreated())
                .pDeadline(projectDto.getPDeadline())
                .pPeople(projectDto.getPPeople())
                .build();

        return entity;
    }

    @Override
    public ProjectDto toDto(ProjectEntity projectEntity) {
        ProjectDto dto = new ProjectDto();
        dto.setProjectId(projectEntity.getProjectId());
        dto.setPName(projectEntity.getPName());
        dto.setPCategory(projectEntity.getPCategory());
        dto.setPExplain(projectEntity.getPExplain());
        dto.setPCreated(projectEntity.getPCreated());
        dto.setPDeadline(projectEntity.getPDeadline());
        dto.setPPeople(projectEntity.getPPeople());
        return dto;
    }
}

package com.example.project_collatool.converter;

import com.example.project_collatool.db.ProjectEntity;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectConverter implements Converter<ProjectEntity, ProjectDto> {
    private final ProjectRepository projectRepository;
    ProjectEntity entity;
    @Override
    public ProjectEntity toEntity(ProjectDto projectDto) {
        if(projectDto.getProjectId() != null){
            Optional<ProjectEntity> byId = projectRepository.findById(projectDto.getProjectId());
            entity = ProjectEntity.builder()
                    .projectId(byId.get().getProjectId())
                    .pName(projectDto.getPName())
                    .pCategory(projectDto.getPCategory())
                    .pDescript(projectDto.getPDescript())
                    .pCreated(LocalDate.parse(projectDto.getPCreated(),DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                    .pDeadline(LocalDate.parse(projectDto.getPDeadline(),DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                    .pPeople(1)
                    .build();
        }else{
            entity = ProjectEntity.builder()
                    .pName(projectDto.getPName())
                    .pCategory(projectDto.getPCategory())
                    .pDescript(projectDto.getPDescript())
                    .pCreated(LocalDate.parse(projectDto.getPCreated(),DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                    .pDeadline(LocalDate.parse(projectDto.getPDeadline(),DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                    .pPeople(1)
                    .build();
        }


        return entity;
    }

    @Override
    public ProjectDto toDto(ProjectEntity projectEntity) {
        ProjectDto dto = new ProjectDto();
        dto.setProjectId(projectEntity.getProjectId());
        dto.setPName(projectEntity.getPName());
        dto.setPCategory(projectEntity.getPCategory());
        dto.setPDescript(projectEntity.getPDescript());
        dto.setPCreated(projectEntity.getPCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dto.setPDeadline(projectEntity.getPDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dto.setPPeople(projectEntity.getPPeople());
        return dto;
    }
}

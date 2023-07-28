package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private Integer projectId;
    private String pName;
    private String pCategory;
    private String pExplain;
    private LocalDateTime pCreated;
    private LocalDateTime pDeadline;
    private Integer pPeople;
}
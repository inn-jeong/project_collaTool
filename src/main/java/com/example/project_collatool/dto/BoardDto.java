package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Integer bId;
    private Integer bProjectId;
    private String bUId;
    private String bTitle;
    private String bContent;
    private LocalDateTime bCreated;
    private String bFname;
    private Integer bFsize;
}

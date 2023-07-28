package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long bId;
    private Long bProjectId;
    private Long bUserId;
    private String bTitle;
    private String bContent;
    private String bFname;
    private Integer bFsize;
}

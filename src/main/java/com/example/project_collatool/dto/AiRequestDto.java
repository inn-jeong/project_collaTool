package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiRequestDto {
    private Integer aiId;
    private Integer aiProjectId;
    private String aiContent;
}

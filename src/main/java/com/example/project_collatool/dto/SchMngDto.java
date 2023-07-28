package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchMngDto {
    private Integer schId;
    private Integer userId;
    private String schTitle;
    private LocalDateTime schCreated;
    private LocalDateTime schDeadline;
}

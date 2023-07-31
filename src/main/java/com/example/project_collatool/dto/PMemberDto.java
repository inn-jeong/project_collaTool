package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PMemberDto {
    private Integer id;
    private Integer projectId;
    private Integer userId;
    private Integer u_position;
}
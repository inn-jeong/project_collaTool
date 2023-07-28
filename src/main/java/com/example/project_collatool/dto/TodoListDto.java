package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListDto {

    private Integer tdId;
    private Integer projectId;
    private Integer userId;
    private String tdTitle;
    private Integer tdCheck;
}
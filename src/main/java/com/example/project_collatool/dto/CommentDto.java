package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Integer cId;
    private Integer bId;
    private Integer userId;
    private String cContent;
}

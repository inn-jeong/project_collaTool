package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Integer cId;
    private Integer bId;
    private String uId;
    private String cContent;
    private LocalDateTime cCreated;
    private String cCreatedStr;
}

package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    int number;
    int size;
    int numberOfElements;
    List<String> content;
    boolean hasContent;
    Sort sort;
}

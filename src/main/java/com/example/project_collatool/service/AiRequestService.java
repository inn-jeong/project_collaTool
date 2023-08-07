package com.example.project_collatool.service;

import com.example.project_collatool.dto.AiRequestDto;

public interface AiRequestService {
    String checkContent(Integer projectId);
    void insertContent(Integer projectId, String content);
    void deleteContent(Integer projectId);
}

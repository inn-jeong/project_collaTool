package com.example.project_collatool.service;

import com.example.project_collatool.db.ProjectEntity;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.UserDto;

import java.util.List;

public interface MainService {
    List<ProjectDto> findAll(String uId);
    UserDto findUser(String uId);
    void updateUser(UserDto user);
}

package com.example.project_collatool.service;

import com.example.project_collatool.db.UserEntity;
import com.example.project_collatool.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Map;

public interface LoginService {
    int loginCheck(UserDto userDto);
    void userInsert(UserDto userDto);
    int userCheck(String uId);
    Map<String, String> validateHandling(Errors errors);
    UserEntity create(UserDto userDto);

}

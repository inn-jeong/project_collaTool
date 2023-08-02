package com.example.project_collatool.service;

import com.example.project_collatool.converter.Converter;
import com.example.project_collatool.converter.UserConverter;
import com.example.project_collatool.db.UserEntity;
import com.example.project_collatool.dto.UserDto;
import com.example.project_collatool.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("LoginService")
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int loginCheck(UserDto userDto) {
        Optional<UserEntity> byUId = userRepository.findByuId(userDto.getUId());
        int re = 0;
        if(!byUId.isEmpty()){
            log.info("@# not empty");
            boolean equals = byUId.get().getUPwd().equals(userDto.getUPwd());
            if(equals) re = 1;
        }
        return re;
    }

    @Override
    public void userInsert(UserDto userDto) {
        userRepository.save(userConverter.toEntity(userDto));
    }

    @Override
    public int userCheck(String uId) {
        Optional<UserEntity> byUId = userRepository.findByuId(uId);
        if(byUId.isEmpty()){
            return 0;
        }
        return 1;
    }

    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        //에러가 있는 값은 이름 앞에 "valid_" 가 붙음
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Override
    public UserEntity create(UserDto userDto) {
        UserEntity userEntity = UserEntity.builder()
                .uId(userDto.getUId())
                .uPwd(passwordEncoder.encode(userDto.getUPwd()))
                .uEmail(userDto.getUEmail())
                .uName(userDto.getUName())
                .uJumin(userDto.getUJumin())
                .uPhone(userDto.getUPhone())
                .uSignout(0)
                .build();
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserDto findId(String uEmail) {
        UserEntity entity = userRepository.findByuEmail(uEmail).get();

        return userConverter.toDto(entity);
    }
}

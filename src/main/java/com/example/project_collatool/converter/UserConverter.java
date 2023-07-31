package com.example.project_collatool.converter;

import com.example.project_collatool.db.UserEntity;
import com.example.project_collatool.dto.UserDto;
import com.example.project_collatool.dto.request.UserRequestDto;
import com.example.project_collatool.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserConverter implements Converter<UserEntity,UserDto>{

    private final UserRepository userRepository;
    @Override
    public UserEntity toEntity(UserDto userDto) {
        UserEntity entity;
        if(userDto.getUserId() != null){

            //null 값에 대한 안전성을 보장하기 위함
            Optional<UserEntity> byId = userRepository.findById(userDto.getUserId());
            entity = UserEntity.builder()
                    .userId(byId.get().getUserId())
                    .uId(userDto.getUId())
                    .uSocial(userDto.getUSocial())
                    .uPwd(userDto.getUPwd())
                    .uEmail(userDto.getUEmail())
                    .uName(userDto.getUName())
                    .uJumin(userDto.getUJumin())
                    .uPhone(userDto.getUPhone())
                    .uCreated(userDto.getUCreate())
                    .uSignout(userDto.getUSignout())
                    .build();
        }else{
            entity = UserEntity.builder()
                    .uId(userDto.getUId())
                    .uSocial(userDto.getUSocial())
                    .uPwd(userDto.getUPwd())
                    .uEmail(userDto.getUEmail())
                    .uName(userDto.getUName())
                    .uJumin(userDto.getUJumin())
                    .uPhone(userDto.getUPhone())
                    .uSignout(0)
                    .build();
        }


        return entity;
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setUserId(entity.getUserId());
        dto.setUId(entity.getUId());
        dto.setUSocial(entity.getUSocial());
        dto.setUPwd(entity.getUPwd());
        dto.setUEmail(entity.getUEmail());
        dto.setUName(entity.getUName());
        dto.setUJumin(entity.getUJumin());
        dto.setUPhone(entity.getUPhone());
        dto.setUCreate(entity.getUCreated());
        dto.setUSignout(entity.getUSignout());

        return dto;
    }

    public UserDto toDto(UserRequestDto requestDto){
        UserDto dto = new UserDto();
        dto.setUId(requestDto.getUId());
        dto.setUSocial(requestDto.getUSocial());
        dto.setUPwd(requestDto.getUPwd());
        dto.setUEmail(requestDto.getUEmail());
        dto.setUName(requestDto.getUName());
        dto.setUJumin(Integer.parseInt(requestDto.getUJumin()));
        dto.setUPhone(Integer.parseInt(requestDto.getUPhone()));

        return dto;
    }
}

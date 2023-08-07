package com.example.project_collatool.service;

import com.example.project_collatool.converter.ProjectConverter;
import com.example.project_collatool.converter.UserConverter;
import com.example.project_collatool.db.PMemberEntity;
import com.example.project_collatool.db.ProjectEntity;
import com.example.project_collatool.db.UserEntity;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.UserDto;
import com.example.project_collatool.repository.PMemberRepository;
import com.example.project_collatool.repository.ProjectRepository;
import com.example.project_collatool.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("MainService")
@RequiredArgsConstructor
@Slf4j
public class MainServiceImpl implements MainService{

    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PMemberRepository pMemberRepository;

    @Override
    public List<ProjectDto> findAll(String uId) {
//        List<ProjectEntity> projectEntityList = projectRepository.findAll();
        List<PMemberEntity> userList = pMemberRepository.findByUserId(userRepository.findByuId(uId).get().getUserId());
        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(PMemberEntity pMember :userList){
            projectDtoList.add(projectConverter.toDto(projectRepository.findByProjectId(pMember.getProjectId()).get()));
        }

        return projectDtoList;
    }

    @Override
    public UserDto findUser(String uId) {
        return userConverter.toDto(userRepository.findByuId(uId).get());
    }

    @Override
    public void updateUser(UserDto user) {
        userRepository.updateByUserId(user.getUserId(),user.getUPwd(),user.getUEmail(),user.getUJumin(),user.getUPhone());
    }
}

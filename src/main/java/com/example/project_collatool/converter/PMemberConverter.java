package com.example.project_collatool.converter;

import com.example.project_collatool.db.PMemberEntity;
import com.example.project_collatool.dto.PMemberDto;
import com.example.project_collatool.repository.PMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PMemberConverter implements Converter<PMemberEntity, PMemberDto> {
    private final PMemberRepository pMemberRepository;
    @Override
    public PMemberEntity toEntity(PMemberDto pMemberDto) {
        Optional<PMemberEntity> byId = pMemberRepository.findById(pMemberDto.getId());

        PMemberEntity entity = PMemberEntity.builder()
                .id(byId.get().getId())
                .projectId(pMemberDto.getProjectId())
                .userId(pMemberDto.getUserId())
                .build();

        return entity;
    }

    @Override
    public PMemberDto toDto(PMemberEntity pMemberEntity) {
        PMemberDto dto = new PMemberDto();
        dto.setId(pMemberEntity.getId());
        dto.setProjectId(pMemberEntity.getProjectId());
        dto.setUserId(pMemberEntity.getUserId());
        
        return dto;
    }
}

package com.example.project_collatool.converter;

import com.example.project_collatool.db.AiRequestEntity;
import com.example.project_collatool.db.BoardEntity;
import com.example.project_collatool.db.UserEntity;
import com.example.project_collatool.dto.AiRequestDto;
import com.example.project_collatool.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AiRequestConverter implements Converter<AiRequestEntity, AiRequestDto>{
    @Override
    public AiRequestEntity toEntity(AiRequestDto aiRequestDto) {
        AiRequestEntity entity;
        if(aiRequestDto.getAiId() != null){
            //null 값에 대한 안전성을 보장하기 위함
            entity = AiRequestEntity.builder()
                    .aiId(aiRequestDto.getAiId())
                    .aiProjectId(aiRequestDto.getAiProjectId())
                    .aiContent(aiRequestDto.getAiContent())
                    .build();
        }else{
            entity = AiRequestEntity.builder()
                    .aiProjectId(aiRequestDto.getAiProjectId())
                    .aiContent(aiRequestDto.getAiContent())
                    .build();
        }
        return entity;
    }

    @Override
    public AiRequestDto toDto(AiRequestEntity aiRequestEntity) {
        AiRequestDto dto = new AiRequestDto();
        dto.setAiId(aiRequestEntity.getAiId());
        dto.setAiProjectId(aiRequestEntity.getAiProjectId());
        dto.setAiContent(aiRequestEntity.getAiContent());
        return dto;
    }
}

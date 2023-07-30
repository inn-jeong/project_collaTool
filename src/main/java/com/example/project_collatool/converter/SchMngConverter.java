package com.example.project_collatool.converter;

import com.example.project_collatool.db.SchMngEntity;
import com.example.project_collatool.dto.SchMngDto;
import com.example.project_collatool.repository.SchMngRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchMngConverter implements Converter<SchMngEntity, SchMngDto> {
    private final SchMngRepository schMngRepository;
    @Override
    public SchMngEntity toEntity(SchMngDto schMngDto) {
        Optional<SchMngEntity> byId =  schMngRepository.findById(schMngDto.getSchId());

        SchMngEntity entity = SchMngEntity.builder()
                .schId(byId.get().getSchId())
                .userId(schMngDto.getUserId())
                .schTitle(schMngDto.getSchTitle())
                .schCreated(schMngDto.getSchCreated())
                .schDeadline(schMngDto.getSchDeadline())
                .build();

        return entity;
    }

    @Override
    public SchMngDto toDto(SchMngEntity schMngEntity) {
        SchMngDto dto = new SchMngDto();
        dto.setSchId(schMngEntity.getSchId());
        dto.setUserId(schMngEntity.getUserId());
        dto.setSchTitle(schMngEntity.getSchTitle());
        dto.setSchCreated(schMngEntity.getSchCreated());
        dto.setSchDeadline(schMngEntity.getSchDeadline());
        return dto;
    }
}

package com.example.project_collatool.converter;

import com.example.project_collatool.db.CommentEntity;
import com.example.project_collatool.dto.CommentDto;
import com.example.project_collatool.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentConverter implements Converter<CommentEntity, CommentDto> {
    private final CommentRepository commentRepository;
    @Override
    public CommentEntity toEntity(CommentDto commentDto) {
//        Optional<CommentEntity> byId = commentRepository.findById(commentDto.getCId());
        CommentEntity entity;
        if(commentDto.getCId() != null){
            entity = CommentEntity.builder()
                    .cId(commentDto.getCId())
                    .bId(commentDto.getBId())
                    .uId(commentDto.getUId())
                    .cContent(commentDto.getCContent())
                    .cCreated(commentDto.getCCreated())
                    .build();
        }else{
            entity = CommentEntity.builder()
                    .bId(commentDto.getBId())
                    .uId(commentDto.getUId())
                    .cContent(commentDto.getCContent())
                    .cCreated(LocalDateTime.now())
                    .build();
        }

        return entity;
    }

    @Override
    public CommentDto toDto(CommentEntity commentEntity) {
        CommentDto dto = new CommentDto();
        dto.setCId(commentEntity.getCId());
        dto.setBId(commentEntity.getBId());
        dto.setUId(commentEntity.getUId());
        dto.setCCreated(commentEntity.getCCreated());
        dto.setCContent(commentEntity.getCContent());
        dto.setCCreatedStr(commentEntity.getCCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        return dto;
    }
}

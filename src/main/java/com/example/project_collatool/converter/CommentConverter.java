package com.example.project_collatool.converter;

import com.example.project_collatool.db.CommentEntity;
import com.example.project_collatool.dto.CommentDto;
import com.example.project_collatool.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentConverter implements Converter<CommentEntity, CommentDto> {
    private final CommentRepository commentRepository;
    @Override
    public CommentEntity toEntity(CommentDto commentDto) {
        Optional<CommentEntity> byId = commentRepository.findById(commentDto.getCId());

        CommentEntity entity = CommentEntity.builder()
                .cId(byId.get().getCId())
                .bId(commentDto.getBId())
                .userId(commentDto.getUserId())
                .cContent(commentDto.getCContent())
                .build();

        return entity;
    }

    @Override
    public CommentDto toDto(CommentEntity commentEntity) {
        CommentDto dto = new CommentDto();
        dto.setCId(commentEntity.getCId());
        dto.setBId(commentEntity.getBId());
        dto.setUserId(commentEntity.getUserId());
        dto.setCContent(commentEntity.getCContent());

        return dto;
    }
}

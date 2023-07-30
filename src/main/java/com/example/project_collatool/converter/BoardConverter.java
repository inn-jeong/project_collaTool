package com.example.project_collatool.converter;

import com.example.project_collatool.db.BoardEntity;
import com.example.project_collatool.dto.BoardDto;
import com.example.project_collatool.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardConverter implements Converter<BoardEntity, BoardDto> {
    private final BoardRepository boardRepository;
    @Override
    public BoardEntity toEntity(BoardDto boardDto) {
        Optional<BoardEntity> byId = boardRepository.findById(boardDto.getBId());

        BoardEntity entity = BoardEntity.builder()
                .bId(byId.get().getBId())
                .bProjectId(boardDto.getBProjectId())
                .bUserId(boardDto.getBUserId())
                .bTitle(boardDto.getBTitle())
                .bContent(boardDto.getBContent())
                .bFname(boardDto.getBFname())
                .bFsize(boardDto.getBFsize())
                .build();
        return entity;
    }

    @Override
    public BoardDto toDto(BoardEntity boardEntity) {
        BoardDto dto = new BoardDto();
        dto.setBId(boardEntity.getBId());
        dto.setBProjectId(boardEntity.getBProjectId());
        dto.setBUserId(boardEntity.getBUserId());
        dto.setBTitle(boardEntity.getBTitle());
        dto.setBContent(boardEntity.getBContent());
        dto.setBFname(boardEntity.getBFname());
        dto.setBFsize(boardEntity.getBFsize());
        return dto;
    }
}

package com.example.project_collatool.converter;

import com.example.project_collatool.db.BoardEntity;
import com.example.project_collatool.dto.BoardDto;
import com.example.project_collatool.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardConverter implements Converter<BoardEntity, BoardDto> {
    private final BoardRepository boardRepository;
    @Override
    public BoardEntity toEntity(BoardDto boardDto) {
        BoardEntity entity;
        if(boardDto.getBId() != null){

            Optional<BoardEntity> byId = boardRepository.findById(boardDto.getBId());
            if(boardDto.getBFname() != null){
                entity = BoardEntity.builder()
                        .bId(byId.get().getBId())
                        .bProjectId(boardDto.getBProjectId())
                        .bUId(boardDto.getBUId())
                        .bTitle(boardDto.getBTitle())
                        .bContent(boardDto.getBContent())
                        .bCreated(boardDto.getBCreated())
                        .bFname(boardDto.getBFname())
                        .bFsize(boardDto.getBFsize())
                        .build();
            }else{
                entity = BoardEntity.builder()
                        .bId(byId.get().getBId())
                        .bProjectId(boardDto.getBProjectId())
                        .bUId(boardDto.getBUId())
                        .bTitle(boardDto.getBTitle())
                        .bContent(boardDto.getBContent())
                        .bCreated(LocalDateTime.now())
                        .build();
            }
        }else{
            if(boardDto.getBFname() != null){
                entity = BoardEntity.builder()
                        .bProjectId(boardDto.getBProjectId())
                        .bUId(boardDto.getBUId())
                        .bTitle(boardDto.getBTitle())
                        .bContent(boardDto.getBContent())
                        .bCreated(boardDto.getBCreated())
                        .bFname(boardDto.getBFname())
                        .bFsize(boardDto.getBFsize())
                        .build();
            }else{
                entity = BoardEntity.builder()
                        .bProjectId(boardDto.getBProjectId())
                        .bUId(boardDto.getBUId())
                        .bTitle(boardDto.getBTitle())
                        .bContent(boardDto.getBContent())
                        .bCreated(LocalDateTime.now())
                        .build();
            }
        }
        return entity;
    }

    @Override
    public BoardDto toDto(BoardEntity boardEntity) {
        BoardDto dto = new BoardDto();
        dto.setBId(boardEntity.getBId());
        dto.setBProjectId(boardEntity.getBProjectId());
        dto.setBUId(boardEntity.getBUId());
        dto.setBTitle(boardEntity.getBTitle());
        dto.setBContent(boardEntity.getBContent());
        dto.setBCreated(boardEntity.getBCreated());
        dto.setBFname(boardEntity.getBFname());
        dto.setBFsize(boardEntity.getBFsize());
        return dto;
    }
}

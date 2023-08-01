package com.example.project_collatool.converter;

import com.example.project_collatool.db.TodoListEntity;
import com.example.project_collatool.dto.TodoListDto;
import com.example.project_collatool.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoListConverter implements Converter<TodoListEntity, TodoListDto> {
    private final TodoListRepository todoListRepository;

    @Override
    public TodoListEntity toEntity(TodoListDto todoListDto) {
        Optional<TodoListEntity> byId = todoListRepository.findById(todoListDto.getTdId());
        TodoListEntity entity;
        if(!byId.isEmpty()){
        entity = TodoListEntity.builder()
                .tdId(byId.get().getTdId())
                .projectId(todoListDto.getProjectId())
                .uId(todoListDto.getUId())
                .tdTitle(todoListDto.getTdTitle())
                .tdCheck(todoListDto.getTdCheck())
                .build();
        }else{
            entity = TodoListEntity.builder()
                    .projectId(todoListDto.getProjectId())
                    .uId(todoListDto.getUId())
                    .tdTitle(todoListDto.getTdTitle())
                    .tdCheck(todoListDto.getTdCheck())
                    .build();
        }

        return entity;
    }

    @Override
    public TodoListDto toDto(TodoListEntity todoListEntity) {
        TodoListDto dto = new TodoListDto();
        dto.setTdId(todoListEntity.getTdId());
        dto.setProjectId(todoListEntity.getProjectId());
        dto.setUId(todoListEntity.getUId());
        dto.setTdTitle(todoListEntity.getTdTitle());
        dto.setTdCheck(todoListEntity.getTdCheck());
        return dto;
    }
}

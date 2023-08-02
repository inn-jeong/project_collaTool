package com.example.project_collatool.service;


import com.example.project_collatool.converter.BoardConverter;
import com.example.project_collatool.converter.ProjectConverter;
import com.example.project_collatool.converter.TodoListConverter;
import com.example.project_collatool.db.BoardEntity;
import com.example.project_collatool.db.TodoListEntity;
import com.example.project_collatool.dto.BoardDto;
import com.example.project_collatool.dto.ProjectDto;
import com.example.project_collatool.dto.TodoListDto;
import com.example.project_collatool.repository.BoardRepository;
import com.example.project_collatool.repository.ProjectRepository;
import com.example.project_collatool.repository.TodoListRepository;
import com.example.project_collatool.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("ProjectService")
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService{
    private final TodoListRepository todoListRepository;
    private final TodoListConverter todoListConverter;
    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;
    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;
    private final UserRepository userRepository;

    @Override
    public void insertProject(ProjectDto projectDto) {
        projectDto.setPCreated(projectDto.getPCreated());
        projectRepository.save(projectConverter.toEntity(projectDto));
    }

    @Override
    public ProjectDto findById(Integer projectId) {
        return projectConverter.toDto(projectRepository.findById(projectId).get());
    }

    @Override
    public List<TodoListDto> findByUIdAndProjectId(String uId, Integer projectId) {
        List<TodoListEntity> todoEntityList = todoListRepository.findByuIdAndProjectId(uId,projectId);
        List<TodoListDto> todoDtoList = new ArrayList<>();
        for (TodoListEntity entity: todoEntityList) {
            todoDtoList.add(todoListConverter.toDto(entity));
        }
        return todoDtoList;
    }

    @Override
    public void saveTodoList(List<String> tdtitles, Integer projectId, String uId) {
        for (String title:tdtitles) {
            if(!title.equals("")){
                TodoListEntity entity = TodoListEntity.builder()
                        .projectId(projectId)
                        .uId(uId)
                        .tdTitle(title)
                        .tdCheck(0)
                        .build();
                todoListRepository.save(entity);
            }
        }
    }

    @Override
    public void updateTodoList(List<Integer> uIds) {
        for(Integer uId:uIds){
            todoListRepository.updateTodoList(uId);
        }
    }

    @Override
    public List<BoardDto> selectAllBoard(Integer bProjectId) {
        List<BoardEntity> entityList = boardRepository.findBybProjectId(bProjectId);
        List<BoardDto> dtoList = new ArrayList<>();
        for(BoardEntity entity : entityList){
            dtoList.add(boardConverter.toDto(entity));
        }
        return dtoList;
    }

    @Override
    public void saveBoard(BoardDto boardDto) {
        boardRepository.save(boardConverter.toEntity(boardDto));
    }

    @Override
    public BoardDto selectBoard(Integer bProjectId, String uId) {
        Optional<BoardEntity> entity = boardRepository.selectBoard(bProjectId,uId);

        return boardConverter.toDto(entity.get());
    }

    @Override
    public BoardDto selectBoard(Integer bId) {
        Optional<BoardEntity> entity = boardRepository.findById(bId);
        return boardConverter.toDto(entity.get());
    }

    @Override
    public void updateBoard(Integer bId, String bTitle, String bContent) {
        log.info("@# update bid ===>"+bId);
        boardRepository.updateBoard(bId,bTitle,bContent);
    }

    @Override
    public void deleteBoard(Integer bId) {
        boardRepository.deleteById(bId);
    }
}

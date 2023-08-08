package com.example.project_collatool.service;


import com.example.project_collatool.converter.*;
import com.example.project_collatool.db.*;
import com.example.project_collatool.dto.*;
import com.example.project_collatool.repository.*;
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
    private final UserConverter userConverter;
    private final PMemberRepository pMemberRepository;
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    @Override
    public void insertProject(ProjectDto projectDto, String uId) {
        projectDto.setPCreated(projectDto.getPCreated());
        projectRepository.save(projectConverter.toEntity(projectDto));

        Optional<ProjectEntity> project = projectRepository.findBypName(projectDto.getPName());
        PMemberEntity member = PMemberEntity.builder()
                        .projectId(project.get().getProjectId())
                        .userId(userRepository.findByuId(uId).get().getUserId())
                        .uPosition("팀장")
                        .build();
        pMemberRepository.save(member);
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

    @Override
    public List<BoardDto> selectBoardSearch(Integer bProjectId, String keyword) {
        List<BoardEntity> entityList = boardRepository.selectBoardSearch(bProjectId,keyword);
        List<BoardDto> dtoList = new ArrayList<>();
        for(BoardEntity entity : entityList){
            dtoList.add(boardConverter.toDto(entity));
        }
        return dtoList;
    }

    @Override
    public List<ProjectMember> findMembers(Integer projectId) {
        List<PMemberEntity> entities = pMemberRepository.findByProjectId(projectId);
        List<ProjectMember> memberList = new ArrayList<>();
        for(PMemberEntity entity:entities){
            ProjectMember user = userConverter.toMember(userRepository.findByUserId(entity.getUserId()).get());
            user.setUPosition(entity.getUPosition());
            memberList.add(user);
        }
        return memberList;
    }

    @Override
    public UserDto findUserByUid(String uId) {
        return userConverter.toDto(userRepository.findByuId(uId).get());
    }

    @Override
    public List<UserDto> selectMembers(Integer projectId) {
        List<UserEntity> entityList = userRepository.selectMembers(projectId);
        List<UserDto> dtos = new ArrayList<>();
        for(UserEntity entity:entityList){
            dtos.add(userConverter.toDto(entity));
        }
        return dtos;
    }

    @Override
    public void addMember(List<Integer> userId, Integer projectId) {
        for(Integer userid: userId){
            PMemberEntity entity = PMemberEntity.builder()
                    .userId(userid)
                    .projectId(projectId)
                    .uPosition("팀원")
                    .build();
            pMemberRepository.save(entity);
        }
    }

    @Override
    public List<CommentDto> selectAllComment(Integer bId) {
        List<CommentEntity> entityList = commentRepository.selectBybId(bId);
        List<CommentDto> dtoList = new ArrayList<>();
        for(CommentEntity entity: entityList){
            dtoList.add(commentConverter.toDto(entity));
        }
        return dtoList;
    }

    @Override
    public void insertComment(CommentDto dto) {
        log.info("@# insert-comment ===>"+dto);
        commentRepository.save(commentConverter.toEntity(dto));
    }

    @Override
    public void deleteComment(Integer cId) {
        commentRepository.deleteCommentByCId(cId);
    }

    @Override
    public CommentDto selectMaxcId() {
        return commentConverter.toDto(commentRepository.selectMaxcId().get());
    }
}

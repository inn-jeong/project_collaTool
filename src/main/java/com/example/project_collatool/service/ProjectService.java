package com.example.project_collatool.service;

import com.example.project_collatool.db.BoardEntity;
import com.example.project_collatool.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    void insertProject(ProjectDto projectDto,String uId);
    ProjectDto findById(Integer projectId);
    List<TodoListDto> findByUIdAndProjectId(String uId, Integer projectId);
    void saveTodoList(List<String> tdtitles, Integer projectId, String uId);
    void updateTodoList(List<Integer> uIds);
    List<BoardDto> selectAllBoard(Integer bProjectId);
    void saveBoard(BoardDto boardDto);
    public BoardDto selectBoard(Integer bProjectId, String uId);
    BoardDto selectBoard(Integer bId);
    void updateBoard(Integer bId, String bTitle, String bContent);
    void deleteBoard(Integer bId);
    List<BoardDto> selectBoardSearch(Integer bProjectId, String keyword);
    List<ProjectMember> findMembers(Integer projectId);
    UserDto findUserByUid(String uId);
    List<UserDto> selectMembers(Integer projectId);
    void addMember(List<Integer> userId, Integer projectId);
}

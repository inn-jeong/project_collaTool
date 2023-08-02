package com.example.project_collatool.repository;

import com.example.project_collatool.db.TodoListEntity;
import com.example.project_collatool.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoListEntity, Integer> {
    @Query("select t from TodoListEntity t where t.uId=:uId and t.projectId=:projectId and t.tdCheck=0")
    public List<TodoListEntity> findByuIdAndProjectId(String uId, Integer projectId);
    @Query("update TodoListEntity t set t.tdCheck=1 where t.tdId=:tdId")
    public int updateTodoList(Integer tdId);
}

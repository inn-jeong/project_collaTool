package com.example.project_collatool.repository;

import com.example.project_collatool.db.TodoListEntity;
import com.example.project_collatool.db.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoListEntity, Integer> {
    @Query("select t from TodoListEntity t where t.uId=:uId and t.projectId=:projectId and t.tdCheck=0")
    public List<TodoListEntity> findByuIdAndProjectId(@Param("uId") String uId,@Param("projectId") Integer projectId);

    @Transactional
    @Modifying
    @Query(value = "update TodoListEntity t set t.tdCheck=1 where t.tdId=:tdId")
    public void updateTodoList(@Param("tdId") Integer tdId);
}

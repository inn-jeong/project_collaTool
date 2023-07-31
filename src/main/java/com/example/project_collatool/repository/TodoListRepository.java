package com.example.project_collatool.repository;

import com.example.project_collatool.db.TodoListEntity;
import com.example.project_collatool.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoListEntity, Integer> {
    public List<TodoListEntity> findByUserId(Integer userId);
}

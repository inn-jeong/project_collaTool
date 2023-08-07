package com.example.project_collatool.repository;

import com.example.project_collatool.db.CommentEntity;
import com.example.project_collatool.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query("select c from CommentEntity c where c.bId=:bId")
    List<CommentEntity> selectBybId(@Param("bId") Integer bId);

    @Transactional
    @Modifying
    @Query("delete from CommentEntity c where c.cId=:cId")
    void deleteCommentByCId(@Param("cId") Integer cId);
}

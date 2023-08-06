package com.example.project_collatool.repository;

import com.example.project_collatool.db.ProjectEntity;
import com.example.project_collatool.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    Optional<ProjectEntity> findByProjectId(Integer projectId);

    @Query("select p from ProjectEntity p " +
            "where p.pName = :pName")
    Optional<ProjectEntity> findBypName(@Param("pName") String pName);
}

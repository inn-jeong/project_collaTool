package com.example.project_collatool.repository;

import com.example.project_collatool.db.ProjectEntity;
import com.example.project_collatool.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
}

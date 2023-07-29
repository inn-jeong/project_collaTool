package com.example.project_collatool.repository;

import com.example.project_collatool.db.PositionEntity;
import com.example.project_collatool.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionEntity, Integer> {
}

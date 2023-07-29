package com.example.project_collatool.repository;

import com.example.project_collatool.db.SchMngEntity;
import com.example.project_collatool.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchMngRepository extends JpaRepository<SchMngEntity, Integer> {
}

package com.example.project_collatool.repository;

import com.example.project_collatool.db.PMemberEntity;
import com.example.project_collatool.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PMemberRepository extends JpaRepository<PMemberEntity, Integer> {
}

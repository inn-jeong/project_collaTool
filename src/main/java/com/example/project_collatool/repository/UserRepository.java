package com.example.project_collatool.repository;

import com.example.project_collatool.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByuId(String uId);
}

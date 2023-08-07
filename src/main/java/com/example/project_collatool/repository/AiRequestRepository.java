package com.example.project_collatool.repository;

import com.example.project_collatool.db.AiRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AiRequestRepository extends JpaRepository<AiRequestEntity, Integer> {
    Optional<AiRequestEntity> findByAiProjectId(Integer aiProjectId);
    @Modifying
    @Transactional
    @Query("delete from AiRequestEntity a where a.aiProjectId=:aiProjectId")
    void deleteContent(@Param("aiProjectId") Integer aiProjectId);
}

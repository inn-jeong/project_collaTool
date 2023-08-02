package com.example.project_collatool.repository;

import com.example.project_collatool.db.BoardEntity;
import com.example.project_collatool.dto.BoardDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    List<BoardEntity> findBybProjectId(Integer bProjectId);
    @Query("select b from BoardEntity b " +
            "where b.bId=" +
            "(select max(b2.bId) " +
            "from BoardEntity b2 " +
            "where b2.bProjectId=:bProjectId " +
            "and b2.bUId=:bUId)")
    Optional<BoardEntity> selectBoard(@Param("bProjectId") Integer bProjectId, @Param("bUId") String bUId);

    @Transactional
    @Modifying
    @Query("update BoardEntity b " +
            "set b.bTitle=:bTitle, " +
            "b.bContent=:bContent " +
            "where b.bId=:bId")
    void updateBoard(@Param("bId") Integer bId,
                                      @Param("bTitle") String bTitle,
                                      @Param("bContent") String bContent);
}

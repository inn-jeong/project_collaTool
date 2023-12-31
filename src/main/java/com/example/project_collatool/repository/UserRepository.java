package com.example.project_collatool.repository;

import com.example.project_collatool.db.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByuId(String uId);
    Optional<UserEntity> findByUserId(Integer userId);
    Optional<UserEntity> findByuEmail(String uEmail);

    @Query("select u from UserEntity u " +
            "left outer join PMemberEntity m on u.userId = m.userId and m.projectId=:projectId " +
            "where m.userId is null ")
    List<UserEntity> selectMembers(@Param("projectId") Integer projectId);

    @Transactional
    @Modifying
    @Query("update UserEntity u " +
            "set u.uPwd=:uPwd, " +
            "u.uEmail=:uEmail, " +
            "u.uPhone=:uPhone " +
            "where u.userId=:userId")
    void updateByUserId(@Param("userId") Integer userId,@Param("uPwd") String uPwd, @Param("uEmail") String uEmail,
                        @Param("uPhone") String uPhone);

}

package com.example.project_collatool.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "u_id", nullable = false)
    private String uId;
    @Column(name = "u_social", nullable = true)
    private String uSocial;
    @Column(name = "u_pwd", nullable = false)
    private String uPwd;
    @Column(name = "u_email", nullable = false)
    private String uEmail;
    @Column(name = "u_name", nullable = false)
    private String uName;
    @Column(name = "u_jumin", nullable = false)
    private Integer uJumin;
    @Column(name = "u_phone", nullable = false)
    private Integer uPhone;
    @Column(name = "u_position", nullable = false)
    private Integer uPosition;
    @Column(name = "u_create", nullable = false)
    private Timestamp uCreate;
    @Column(name = "u_signout", nullable = false)
    private Integer uSignout;
}

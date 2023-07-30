package com.example.project_collatool.db;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.internal.CurrentTimestampGeneration;
import org.springframework.data.annotation.Persistent;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
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

    @CreationTimestamp
    @Column(name = "u_created", nullable = false)
    private Timestamp uCreated;

    @Column(name = "u_signout", nullable = false)
    private Integer uSignout;
}

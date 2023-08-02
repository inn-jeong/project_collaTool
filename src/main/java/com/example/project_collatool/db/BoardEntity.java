package com.example.project_collatool.db;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private Integer bId;

    @Column(name = "b_project_id", nullable = false)
    private Integer bProjectId;

    @Column(name = "b_u_id", nullable = false)
    private String bUId;

    @Column(name = "b_title", nullable = false)
    private String bTitle;

    @Column(name = "b_content", nullable = false)
    private String bContent;

    @Column(name = "b_created", nullable = false)
    private LocalDateTime bCreated;

    @Column(name = "b_fname", nullable = false)
    private String bFname;

    @Column(name = "b_fsize", nullable = false)
    private Integer bFsize;
}

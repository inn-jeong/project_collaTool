package com.example.project_collatool.db;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sch_mng")
public class SchMngEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sch_id")
    private Integer schId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "sch_title", nullable = false)
    private String schTitle;

    @Column(name = "sch_created", nullable = false)
    private LocalDateTime schCreated;

    @Column(name = "sch_deadline", nullable = false)
    private LocalDateTime schDeadline;
}

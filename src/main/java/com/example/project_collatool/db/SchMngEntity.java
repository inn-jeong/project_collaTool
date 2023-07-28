package com.example.project_collatool.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

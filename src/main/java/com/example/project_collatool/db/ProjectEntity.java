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
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "p_name", length = 100, nullable = false)
    private String pName;

    @Column(name = "p_category", length = 50, nullable = false)
    private String pCategory;

    @Column(name = "p_explain", nullable = false, columnDefinition = "TEXT")
    private String pExplain;

    @Column(name = "p_created", nullable = false, columnDefinition = "DATETIME DEFAULT NOW()")
    private LocalDateTime pCreated;

    @Column(name = "p_deadline", nullable = false)
    private LocalDateTime pDeadline;

    @Column(name = "p_people", nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer pPeople;
}
package com.example.project_collatool.db;

import lombok.*;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "p_descript", nullable = false, columnDefinition = "TEXT")
    private String pDescript;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "p_created", nullable = false, columnDefinition = "DATE DEFAULT NOW()")
    private LocalDate pCreated;

    @Column(name = "p_deadline", nullable = false)
    private LocalDate pDeadline;

    @Column(name = "p_people", nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer pPeople;
}
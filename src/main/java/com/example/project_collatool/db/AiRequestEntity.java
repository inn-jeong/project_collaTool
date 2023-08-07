package com.example.project_collatool.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ai_request")
public class AiRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_id")
    private Integer aiId;

    @Column(name = "ai_project_id", nullable = false)
    private Integer aiProjectId;

    @Column(name = "ai_content", nullable = false)
    private String aiContent;

}

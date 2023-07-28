package com.example.project_collatool.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "b_comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Integer cId;

    @Column(name = "b_id", nullable = false)
    private Integer bId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "c_content", nullable = false)
    private String cContent;
}

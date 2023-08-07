package com.example.project_collatool.db;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "b_comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Integer cId;

    @Column(name = "b_id", nullable = false)
    private Integer bId;

    @Column(name = "u_id", nullable = false)
    private String uId;

    @Column(name = "c_content", nullable = false)
    private String cContent;
}

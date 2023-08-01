package com.example.project_collatool.db;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todo_list")
public class TodoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "td_id")
    private Integer tdId;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "u_id", nullable = false)
    private String uId;

    @Column(name = "td_title", nullable = false)
    private String tdTitle;

    @Column(name = "td_check", nullable = false)
    private Integer tdCheck;
}
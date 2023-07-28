package com.example.project_collatool.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo_list")
public class TodoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "td_id")
    private Integer tdId;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "td_title", nullable = false)
    private String tdTitle;

    @Column(name = "td_check", nullable = false)
    private Integer tdCheck;
}
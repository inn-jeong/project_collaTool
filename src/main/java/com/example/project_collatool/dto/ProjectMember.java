package com.example.project_collatool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMember {
    private Integer userId;
    private String uId;
    private String uSocial;
    private String uPwd;
    private String uEmail;
    private String uName;
    private String uJumin;
    private String uPhone;
    private Timestamp uCreate;
    private Integer uSignout;
    private String uPosition;
}

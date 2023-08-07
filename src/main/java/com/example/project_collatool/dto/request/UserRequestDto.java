package com.example.project_collatool.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRequestDto {
    @NotBlank(message = "아이디을 입력해주세요.")
    private String uId;

    private String uSocial;

    @NotBlank(message = "비밀번호을 입력해주세요.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String uPwd;

    @NotBlank(message = "비밀번호를 한번 더 입력해주세요.")
    private String uPwdCheck;

    @Email(message = "이메일 형식에 맞지 않습니다.ex)abc@naver.com")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String uEmail;

    @NotBlank(message = "이름을 입력해주세요.")
    private String uName;

    @NotBlank(message = "생년월일을 입력해주세요.")
    @Positive
    private String uJumin;

    @Pattern(regexp = "01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})$",message = "휴대폰 번호를 잘못 입력하셨습니다.")
    private String uPhone;

    private Integer userId;
    private Integer uSignout;
}

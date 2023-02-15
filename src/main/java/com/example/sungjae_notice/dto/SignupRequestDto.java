package com.example.sungjae_notice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SignupRequestDto {

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}", message = "아이디는 4~10자 영문 소문자, 숫자를 사용하세요.")
    private String username;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}", message = "비밀번호는 8~15자 영문 대 소문자, 숫자를 사용하세요.")
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}

// 아이디, 비밀번호 유효성 검사 추가

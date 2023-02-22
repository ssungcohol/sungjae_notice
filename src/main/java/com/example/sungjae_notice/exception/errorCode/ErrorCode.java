package com.example.sungjae_notice.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    // 클라이언트에게 보내줄 에러코드를 정의 (추상화)

    String name();
    HttpStatus getHttpStatus();
    String getMessage();


}


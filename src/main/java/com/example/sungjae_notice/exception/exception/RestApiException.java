package com.example.sungjae_notice.exception.exception;

import com.example.sungjae_notice.exception.errorCode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {
    // 언체크 예외(런타임 예외)를 상속받는 예외 클래스 추가

    private final ErrorCode errorCode;

}

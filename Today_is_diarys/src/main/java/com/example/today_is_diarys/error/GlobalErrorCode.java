package com.example.today_is_diarys.error;

import com.example.today_is_diarys.error.properties.ErrorProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GlobalErrorCode implements ErrorProperties {

    // 401
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    INVALID_TOKEN_TYPE(401, "유효하지 않은 토큰 타입입니다."),
    USER_CREDENTIALS_NOT_FOUND(401, "유효하지 않은 인증정보입니다."),

    // 403
    FORBIDDEN(403, "접근이 거부되었습니다."),
    NOT_VALID_USER_ROLE(403, "유효하지 않은 사용자 권한입니다."),

    // 409
    MEMBER_NOT_FOUND(409, "유저를 못 찾았습니다."),

    // 500
    INTERNAL_SERVER_ERROR(500, "서버 오류");

    private final int status;
    private final String message;
}

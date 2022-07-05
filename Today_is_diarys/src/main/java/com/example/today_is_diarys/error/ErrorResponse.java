package com.example.today_is_diarys.error;

import com.example.today_is_diarys.error.properties.ErrorProperties;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;

    public ErrorResponse(ErrorProperties errorProperties) {
        this.status = errorProperties.getStatus();
        this.message = errorProperties.getMessage();
    }

}

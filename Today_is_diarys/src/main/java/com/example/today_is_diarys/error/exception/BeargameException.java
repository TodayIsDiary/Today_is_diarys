package com.example.today_is_diarys.error.exception;

import com.example.today_is_diarys.error.properties.ErrorProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BeargameException extends RuntimeException {

    private final ErrorProperties errorProperties;
}

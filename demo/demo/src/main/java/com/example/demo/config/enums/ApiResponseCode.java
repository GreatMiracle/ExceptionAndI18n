package com.example.demo.config.enums;

import org.springframework.http.HttpStatus;

public enum ApiResponseCode {
    SUCCESS(HttpStatus.OK, "200", "GLOBAL.SUCCESS"),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "GLOBAL.SERVER_ERROR"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "GLOBAL.BAD_REQUEST"),
    TEST_I18N(HttpStatus.OK, "200", "GLOBAL.TEST_I18N"),
    INPUT_PARAMS_INVALID(HttpStatus.BAD_REQUEST, "400", "INPUT_PARAMS_INVALID"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ApiResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

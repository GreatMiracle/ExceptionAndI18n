package com.example.demo.util;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.util.exception.RestException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class RestResponseWrapper {

    private RestResponseWrapper() {
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getResponse(HttpStatus status, String code, String message, T data, Long totalResults) {
        BodyResponseDTO<T> bodyResponse = new BodyResponseDTO<>(code, message, data, totalResults);
        return new ResponseEntity<>(bodyResponse, status);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getResponse(HttpStatus status, String code, String message, Page<T> page) {
        BodyResponseDTO<T> bodyResponse = new BodyResponseDTO<>(code, message, page);
        return new ResponseEntity<>(bodyResponse, status);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getResponse(HttpStatus status, String code, String message, T data) {
        return getResponse(status, code, message, data, null);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getSuccess(T data, Long totalResults, MessageUtils messageUtil) {
        ApiResponseCode errorCode = ApiResponseCode.SUCCESS;
        String i18nMessage = errorCode.getMessage();
        if (messageUtil != null) {
            i18nMessage = messageUtil.populate(errorCode.getMessage());
        }
        return getResponse(errorCode.getStatus(), errorCode.getCode(), i18nMessage, data, totalResults);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getSuccess() {
        ApiResponseCode errorCode = ApiResponseCode.SUCCESS;
        BodyResponseDTO<T> bodyResponse = new BodyResponseDTO<>(errorCode.getCode(), errorCode.getMessage(), null, null);
        return new ResponseEntity<>(bodyResponse, errorCode.getStatus());
    }

    /**
     * @author TruongBV
     * push dev by Kiennt74
     * @date 2021/09/09
     * @time 1:45 PM
     */
    public static <T> ResponseEntity<BodyResponseDTO<T>> getSuccess(ApiResponseCode responseCode, T data, Long totalResults, MessageUtils messageUtil) {
        ApiResponseCode errorCode = responseCode == null ? ApiResponseCode.SUCCESS : responseCode;
        String i18nMessage = errorCode.getMessage();
        if (messageUtil != null) {
            i18nMessage = messageUtil.populate(errorCode.getMessage());
        }
        return getResponse(errorCode.getStatus(), errorCode.getCode(), i18nMessage, data, totalResults);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getSuccess(Page<T> page, MessageUtils messageUtil) {
        ApiResponseCode errorCode = ApiResponseCode.SUCCESS;
        String i18nMessage = errorCode.getMessage();
        if (messageUtil != null) {
            i18nMessage = messageUtil.populate(errorCode.getMessage());
        }
        return getResponse(errorCode.getStatus(), errorCode.getCode(), i18nMessage, page);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getSuccess(T data, MessageUtils messageUtil) {
        return getSuccess(data, null, messageUtil);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getSuccess(T data) {
        return getSuccess(data, null, null);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getSuccess(MessageUtils messageUtil) {
        return getSuccess(null, messageUtil);
    }

    /**
     * @author Kiennt74
     * push dev by Kiennt74
     * @date 2021/09/09
     * @time 1:45 PM
     */
    public static <T> ResponseEntity<BodyResponseDTO<T>> getSuccess(ApiResponseCode responseCode, T data, MessageUtils messageUtil) {
        return getSuccess(responseCode, data, null, messageUtil);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getSuccess(Page<T> page) {
        return getSuccess(page, null);
    }

    public static ResponseEntity<BodyResponseDTO<Object>> wrapInternalErrorResponse(String i18nMessage) {
        return getResponse(ApiResponseCode.UNKNOWN_ERROR.getStatus(), ApiResponseCode.UNKNOWN_ERROR.getCode(), i18nMessage);
    }

    public static ResponseEntity<BodyResponseDTO<Object>> wrapInternalErrorResponse(RestException restException, String i18nMessage) {
        return getResponse(restException.getRestError().getStatus(), restException.getRestError().getCode(), i18nMessage);
    }

    public static <T> ResponseEntity<BodyResponseDTO<T>> getResponse(HttpStatus status, String code, String message) {
        BodyResponseDTO<T> bodyResponse = new BodyResponseDTO<>(code, message);
        return new ResponseEntity<>(bodyResponse, status);
    }

}

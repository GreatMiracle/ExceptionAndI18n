package com.example.demo.util.exception;

import com.example.demo.config.enums.ApiResponseCode;
import com.example.demo.util.BodyResponseDTO;
import com.example.demo.util.MessageUtils;
import com.example.demo.util.RestResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageUtils messageUtil;

    @ExceptionHandler(RestException.class)
    public ResponseEntity<BodyResponseDTO<Object>> handleRestException(HttpServletRequest request, RestException ex) {
        logger.error(ex.getMessage(), ex);
        String i18nMessage = messageUtil.populate(ex.getRestError().getMessage(), ex.getParams());
        return RestResponseWrapper.wrapInternalErrorResponse(ex, i18nMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BodyResponseDTO<Object>> handleUnCaughtException(HttpServletRequest request, Exception ex) {
        logger.error(ex.getMessage(), ex);
        String i18nMessage = messageUtil.populate(ApiResponseCode.UNKNOWN_ERROR.getMessage());
        return RestResponseWrapper.wrapInternalErrorResponse(i18nMessage);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        String errorFields = errors.get(0).getDefaultMessage();
        try {
            errorFields = messageUtil.populate(errorFields);
        } catch (Exception e) {
            logger.info(ex);
        }
        logger.error("MethodArgumentNotValidException: " + ex.getClass().getName() + " " + ex.getMessage());
        BodyResponseDTO bodyResponse = new BodyResponseDTO(ApiResponseCode.INPUT_PARAMS_INVALID.getCode(), errorFields, null);
        return new ResponseEntity<>(bodyResponse, ApiResponseCode.INPUT_PARAMS_INVALID.getStatus());
    }
}

package com.tcs.common.exception.impl.expose.web;

import com.tcs.common.exception.impl.model.ApiException;
import com.tcs.common.exception.impl.model.ApiExceptionResponse;
import com.tcs.common.exception.impl.util.mapper.ApiExceptionMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public final ResponseEntity<ApiExceptionResponse> sendException(ApiException exception, WebRequest request) {
    return new ResponseEntity<>(ApiExceptionMapper.buildApiExceptionResponse.apply(exception), exception.getHttpStatus());
  }
}

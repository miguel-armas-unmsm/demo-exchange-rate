package com.tcs.common.exception.impl.expose.web;

import com.tcs.common.exception.impl.model.ApiException;
import com.tcs.common.exception.impl.model.ApiExceptionResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public final ResponseEntity<ApiExceptionResponse> sendException(ApiException ex, WebRequest request) {
    ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse
        .builder(ex.getSystemCode(), ex.getDescription(), ex.getHttpStatus().toString())
        .build();
    return new ResponseEntity<>(apiExceptionResponse, ex.getHttpStatus());
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse
        .builder("MESSAGE_NOT_READABLE", ex.getCause().getMessage(), status.toString())
        .build();
    return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status,
                                                                WebRequest request) {
    List<String> validationList = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());

    ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse
        .builder("ARGUMENT_NOT_VALID", "Los datos proporcionados no son válidos.", status.toString())
        .additionalDetails(validationList)
        .build();

    return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
  }
}

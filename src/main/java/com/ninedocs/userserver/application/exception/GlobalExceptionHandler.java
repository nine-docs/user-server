package com.ninedocs.userserver.application.exception;

import com.ninedocs.userserver.application.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
    return ResponseEntity.badRequest().body(
        ApiResponse.error(e.getErrorCode())
    );
  }
}

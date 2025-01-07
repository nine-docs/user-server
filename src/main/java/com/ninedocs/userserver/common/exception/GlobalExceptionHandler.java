package com.ninedocs.userserver.common.exception;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.EmailFormatException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
    return ResponseEntity.ok().body(
        ApiResponse.error(e.getErrorCode())
    );
  }

  @ExceptionHandler(EmailFormatException.class)
  public ResponseEntity<Object> handleDuplicateEmailException(
      EmailFormatException e) {
    Map<String, Object> body = new HashMap<>();
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", "Bad Request");
    body.put("message", e.getErrorCode());
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}

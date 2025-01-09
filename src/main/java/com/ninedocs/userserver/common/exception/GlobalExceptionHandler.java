package com.ninedocs.userserver.common.exception;

import com.ninedocs.userserver.common.exception.dto.ErrorResponse;
import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.EmailFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
  public ResponseEntity<ErrorResponse> handleValidEmailException(
      EmailFormatException e) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .error("Bad Request")
        .message(e.getMessage())
        .status(HttpStatus.BAD_REQUEST.value())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .error("Bad Request")
        .message(
            e.getBindingResult().hasFieldErrors()
                ? "validation failed"
                : e.getBindingResult().getFieldError().getDefaultMessage()
        )
        .status(HttpStatus.BAD_REQUEST.value())
        .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }


}


package com.ninedocs.userserver.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

  private boolean success;
  private String errorCode;
  private T data;

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(true, null, data);
  }
  
  public static <T> ApiResponse<T> error(String errorCode) {
    return new ApiResponse<>(false, errorCode, null);
  }
}

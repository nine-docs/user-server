package com.ninedocs.userserver.common.exception.dto;

import lombok.Builder;

@Builder
public class ErrorResponse {

  private int status;
  private String error;
  private String message;
}

package com.ninedocs.userserver.user.application.emailverificationcode.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class EmailFormatException extends RuntimeException {

  public String getErrorCode() {
    return "INCORRECT_EMAIL_FORMAT";
  }
}

package com.ninedocs.userserver.application.emailverificationcode.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class EmailFormatException extends CustomException {

  @Override
  public String getErrorCode() {
    return "INCORRECT_EMAIL_FORMAT";
  }
}

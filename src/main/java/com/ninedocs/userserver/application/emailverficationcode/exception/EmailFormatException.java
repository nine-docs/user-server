package com.ninedocs.userserver.application.emailverficationcode.exception;

import com.ninedocs.userserver.application.exception.CustomException;

public class EmailFormatException extends CustomException {

  @Override
  public String getErrorCode() {
    return "INCORRECT_EMAIL_FORMAT";
  }
}

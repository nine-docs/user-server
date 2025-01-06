package com.ninedocs.userserver.application.emailverficationcode.exception;

import com.ninedocs.userserver.application.exception.CustomException;

public class DuplicateEmailException extends CustomException {

  @Override
  public String getErrorCode() {
    return "EMAIL_DUPLICATED";
  }
}
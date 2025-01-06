package com.ninedocs.userserver.application.emailverificationcode.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class DuplicateEmailException extends CustomException {

  @Override
  public String getErrorCode() {
    return "EMAIL_DUPLICATED";
  }
}
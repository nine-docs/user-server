package com.ninedocs.userserver.user.application.emailverificationcode.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class DuplicateEmailException extends CustomException {

  public String getErrorCode() {

    return "EMAIL_DUPLICATED";
  }
}
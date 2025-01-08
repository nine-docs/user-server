package com.ninedocs.userserver.user.application.signup.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class EmailNotVerifiedException extends CustomException {

  @Override
  public String getErrorCode() {
    return "EMAIL_NOT_VERIFIED";
  }
}

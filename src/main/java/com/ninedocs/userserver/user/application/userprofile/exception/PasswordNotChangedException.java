package com.ninedocs.userserver.user.application.userprofile.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class PasswordNotChangedException extends CustomException {

  @Override
  public String getErrorCode() {
    return "PASSWORD_NOT_CHANGED";
  }
}

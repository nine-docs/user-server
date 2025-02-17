package com.ninedocs.userserver.user.application.userprofile.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class WrongPasswordException extends CustomException {

  @Override
  public String getErrorCode() {
    return "WRONG_PASSWORD";
  }
}

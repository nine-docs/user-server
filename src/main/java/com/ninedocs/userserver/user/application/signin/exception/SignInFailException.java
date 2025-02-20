package com.ninedocs.userserver.user.application.signin.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class SignInFailException extends CustomException {

  @Override
  public String getErrorCode() {
    return "TEST_LOGIN_FAILED";
  }
}

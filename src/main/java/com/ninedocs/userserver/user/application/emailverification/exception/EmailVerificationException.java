package com.ninedocs.userserver.user.application.emailverification.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class EmailVerificationException extends CustomException {

  @Override
  public String getErrorCode() {
    return "VERIFICATION_FAILED";
  }

}

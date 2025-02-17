package com.ninedocs.userserver.user.application.userprofile.exception;

import com.ninedocs.userserver.common.exception.CustomException;

public class UserNotExistException extends CustomException {

  @Override
  public String getErrorCode() {
    return "USER_NOT_EXIST";
  }
}

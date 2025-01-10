package com.ninedocs.userserver.user.application.deleteuser.exception;

public class NullUserException extends RuntimeException {

  public String getErrorCode() {
    return "There is no User";
  }
}

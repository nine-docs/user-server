package com.ninedocs.userserver.application.exception;

public abstract class CustomException extends RuntimeException {

  public abstract String getErrorCode();
}

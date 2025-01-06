package com.ninedocs.userserver.common.exception;

public abstract class CustomException extends RuntimeException {

  public abstract String getErrorCode();
}

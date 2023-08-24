package com.bayada.joy.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class MethodArgumentNotValidException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public MethodArgumentNotValidException(String message) {
    super(message);
  }
  
}

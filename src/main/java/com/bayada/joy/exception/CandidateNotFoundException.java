package com.bayada.joy.exception;

public class CandidateNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CandidateNotFoundException() {

  }

  public CandidateNotFoundException(String id) {
    super("Candidate with id: " + id + " not found");
  }

}

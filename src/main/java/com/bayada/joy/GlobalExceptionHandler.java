package com.bayada.joy;

import java.net.URI;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.bayada.joy.exception.CandidateNotFoundException;
import com.bayada.joy.exception.MethodArgumentNotValidException;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CandidateNotFoundException.class)
  ErrorResponse handleBookmarkNotFoundException(CandidateNotFoundException e) {
    return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage())
        .title("Candidate Not found").type(URI.create("https://api.candidate.com/errors/not-found"))
        .property("errorCategory", "Generic").property("timestamp", Instant.now()).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ErrorResponse handleValidationExceptions(MethodArgumentNotValidException e) {
    return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).title("Invalid format")
        .type(URI.create("https://api.order.com/errors/not-found"))
        .property("errorCategory", "Generic").property("timestamp", Instant.now()).build();
  }

}

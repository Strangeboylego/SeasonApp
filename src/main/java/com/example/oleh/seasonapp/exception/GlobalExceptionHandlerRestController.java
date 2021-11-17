package com.example.oleh.seasonapp.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandlerRestController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(SQLException.class)
  public ResponseEntity<?> handleAuthentication(HttpServletRequest request,
      SQLException exception) {
    return ResponseEntity.badRequest().body("Problem with DB request");
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<?> handleExceptionWithMessage(HttpServletRequest request,
      UserAlreadyExistsException exception) {
    return ResponseEntity.badRequest().body(exception.getMessage());
  }
}

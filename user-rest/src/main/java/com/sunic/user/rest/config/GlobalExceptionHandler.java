package com.sunic.user.rest.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sunic.user.spec.common.rdo.ErrorResponse;
import com.sunic.user.spec.exception.InvalidCredentialsException;
import com.sunic.user.spec.exception.InvalidWorkspaceStateException;
import com.sunic.user.spec.exception.UserAlreadyExistsException;
import com.sunic.user.spec.exception.UserNotFoundException;
import com.sunic.user.spec.exception.UserWorkspaceAlreadyExistsException;
import com.sunic.user.spec.exception.WorkspaceAccessDeniedException;
import com.sunic.user.spec.exception.WorkspaceAlreadyExistsException;
import com.sunic.user.spec.exception.WorkspaceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException e) {
        return new ResponseEntity<>(
            ErrorResponse.from(false, e.getMessage()),
            HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException e) {
        return new ResponseEntity<>(
            ErrorResponse.from(false, e.getMessage()),
            HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
        return new ResponseEntity<>(
            ErrorResponse.from(false, e.getMessage()),
            HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(WorkspaceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWorkspaceNotFound(WorkspaceNotFoundException e) {
        return new ResponseEntity<>(
            ErrorResponse.from(false, e.getMessage()),
            HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(UserWorkspaceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserWorkspaceAlreadyExists(UserWorkspaceAlreadyExistsException e) {
        return new ResponseEntity<>(
            ErrorResponse.from(false, e.getMessage()),
            HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(WorkspaceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleWorkspaceAlreadyExists(WorkspaceAlreadyExistsException e) {
        return new ResponseEntity<>(
            ErrorResponse.from(false, e.getMessage()),
            HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(InvalidWorkspaceStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidWorkspaceState(InvalidWorkspaceStateException e) {
        return new ResponseEntity<>(
            ErrorResponse.from(false, e.getMessage()),
            HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(WorkspaceAccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleWorkspaceAccessDenied(WorkspaceAccessDeniedException e) {
        return new ResponseEntity<>(
            ErrorResponse.from(false, e.getMessage()),
            HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception e) {
        return new ResponseEntity<>(
            ErrorResponse.from(false, "Internal server error"),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
package com.sunic.user.spec.exception;

public class UserWorkspaceAlreadyExistsException extends RuntimeException {
    public UserWorkspaceAlreadyExistsException(String message) {
        super(message);
    }
}
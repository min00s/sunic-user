package com.sunic.user.spec.userworkspace.exception;

public class UserWorkspaceAlreadyExistsException extends RuntimeException {
    public UserWorkspaceAlreadyExistsException(String message) {
        super(message);
    }
}
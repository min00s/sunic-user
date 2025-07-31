package com.sunic.user.spec.exception;

public class WorkspaceAlreadyExistsException extends RuntimeException {
    public WorkspaceAlreadyExistsException(String message) {
        super(message);
    }
}
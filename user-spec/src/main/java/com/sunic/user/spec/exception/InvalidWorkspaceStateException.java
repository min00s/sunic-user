package com.sunic.user.spec.exception;

public class InvalidWorkspaceStateException extends RuntimeException {
    public InvalidWorkspaceStateException(String message) {
        super(message);
    }
}
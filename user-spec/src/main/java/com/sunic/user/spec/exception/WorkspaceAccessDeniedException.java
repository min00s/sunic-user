package com.sunic.user.spec.exception;

public class WorkspaceAccessDeniedException extends RuntimeException {
    public WorkspaceAccessDeniedException(String message) {
        super(message);
    }
}
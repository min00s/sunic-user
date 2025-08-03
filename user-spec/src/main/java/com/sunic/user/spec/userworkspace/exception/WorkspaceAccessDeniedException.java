package com.sunic.user.spec.userworkspace.exception;

public class WorkspaceAccessDeniedException extends RuntimeException {
	public WorkspaceAccessDeniedException(String message) {
		super(message);
	}
}
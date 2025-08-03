package com.sunic.user.spec.userworkspace.exception;

public class WorkspaceAlreadyExistsException extends RuntimeException {
	public WorkspaceAlreadyExistsException(String message) {
		super(message);
	}
}
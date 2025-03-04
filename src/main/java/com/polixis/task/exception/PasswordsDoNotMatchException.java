package com.polixis.task.exception;

public class PasswordsDoNotMatchException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Passwords do not match!";

    public PasswordsDoNotMatchException() {
        super(ERROR_MESSAGE);
    }
}

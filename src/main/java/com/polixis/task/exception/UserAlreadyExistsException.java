package com.polixis.task.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private static final String ERROR_MESSAGE = "User with name: '%s' already exists";

    public UserAlreadyExistsException(String username) {
        super(ERROR_MESSAGE.formatted(username));
    }
}

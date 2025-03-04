package com.polixis.task.exception;

import java.util.List;

public class UserNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "User with name: '%s' was not found.";

    public UserNotFoundException(String username) {
        super(String.format(ERROR_MESSAGE, username));
    }

    public UserNotFoundException(List<Long> ids) {
        super(String.format("Several users from: %s are not found.", ids));
    }
}

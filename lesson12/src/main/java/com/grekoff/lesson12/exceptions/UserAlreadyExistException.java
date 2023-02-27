package com.grekoff.lesson12.exceptions;

import java.util.Objects;

public final class UserAlreadyExistException extends Throwable {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}

package com.grekoff.lesson12.exceptions;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}

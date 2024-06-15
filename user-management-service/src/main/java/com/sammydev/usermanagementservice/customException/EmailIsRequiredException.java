package com.sammydev.usermanagementservice.customException;

public class EmailIsRequiredException extends RuntimeException {
    public EmailIsRequiredException(String message) {
        super(message);
    }
}

package com.sammydev.usermanagementservice.customException;

public class UserAlreadyExistsException extends  RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

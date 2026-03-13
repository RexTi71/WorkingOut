package com.workingout.workingout.exceptions;

public class RegistrationException extends RuntimeException {
    public RegistrationException(){
        super("Failed to create user");
    }
    public RegistrationException(String message) {
        super(message);
    }
}

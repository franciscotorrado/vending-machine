package com.machines.vending.domain.exceptions.user;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException() {
        super("Password is not valid. " +
                "It is must be more than 8 and less than 20 characters long, " +
                "contain uppercase and lowercase letters, numbers " +
                "and at least one special character of '#$%@_-'");
    }
}

package com.machines.vending.domain.exceptions.user;

public class InvalidUsernameException extends Exception {
    public InvalidUsernameException() {
        super("Username is not valid. " +
                "It is must be between 6 and 12 characters long, " +
                "contain uppercase or lowercase letters, numbers and _ " +
                "but no spaces or other special characters");
    }
}

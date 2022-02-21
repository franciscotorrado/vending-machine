package com.machines.vending.domain.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User not found exception");
    }
}

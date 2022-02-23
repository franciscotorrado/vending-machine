package com.machines.vending.domain.exceptions.user;

public class CreateUserException extends Exception {
    public CreateUserException() {
        super("An error happened. Please try again or try with another username.");
    }
}

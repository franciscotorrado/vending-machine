package com.machines.vending.domain.exceptions;

public class CreateUserWithGivenIdException extends Exception {
    public CreateUserWithGivenIdException() {
        super("Is not allowed to create a new user giving its id");
    }
}

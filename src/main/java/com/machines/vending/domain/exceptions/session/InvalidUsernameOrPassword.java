package com.machines.vending.domain.exceptions.session;

public class InvalidUsernameOrPassword extends Exception {
    public InvalidUsernameOrPassword() {
        super("Invalid username or password. Try again!");
    }
}

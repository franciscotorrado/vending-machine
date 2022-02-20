package com.machines.vending.domain.exceptions;

public class InvalidRoleException extends Exception {
    public InvalidRoleException() {
        super("Provided role is not valid");
    }
}

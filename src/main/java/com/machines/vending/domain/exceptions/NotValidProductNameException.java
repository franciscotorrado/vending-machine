package com.machines.vending.domain.exceptions;

public class NotValidProductNameException extends Exception {
    public NotValidProductNameException() {
        super("Empty name is not valid");
    }
}

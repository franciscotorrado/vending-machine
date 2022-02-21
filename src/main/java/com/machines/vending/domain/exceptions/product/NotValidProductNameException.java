package com.machines.vending.domain.exceptions.product;

public class NotValidProductNameException extends Exception {
    public NotValidProductNameException() {
        super("Empty name is not valid");
    }
}

package com.machines.vending.domain.exceptions;

public class InvalidCoinException extends Exception {
    public InvalidCoinException() {
        super("Invalid coin received");
    }
}

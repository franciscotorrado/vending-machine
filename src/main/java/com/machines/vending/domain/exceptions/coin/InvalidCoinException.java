package com.machines.vending.domain.exceptions.coin;

public class InvalidCoinException extends Exception {
    public InvalidCoinException() {
        super("Invalid coin received");
    }
}

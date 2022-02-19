package com.machines.vending.application.exceptions;

public class NotEnoughDepositException extends Exception {
    public NotEnoughDepositException() {
        super("No enough deposit available");
    }
}

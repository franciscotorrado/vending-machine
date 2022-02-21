package com.machines.vending.domain.exceptions.deposit;

public class NotEnoughDepositException extends Exception {
    public NotEnoughDepositException() {
        super("No enough deposit available");
    }
}

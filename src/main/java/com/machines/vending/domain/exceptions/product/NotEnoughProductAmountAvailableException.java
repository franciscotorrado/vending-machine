package com.machines.vending.domain.exceptions.product;

public class NotEnoughProductAmountAvailableException extends Exception {
    public NotEnoughProductAmountAvailableException() {
        super("Available amount is not enough");
    }
}

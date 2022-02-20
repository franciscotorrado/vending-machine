package com.machines.vending.domain.exceptions;

public class NotValidProductCostException extends Exception {
    public NotValidProductCostException() {
        super("Cost is not multiple of five");
    }
}

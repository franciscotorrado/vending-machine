package com.machines.vending.domain.exceptions.product;

public class NotValidProductCostException extends Exception {
    public NotValidProductCostException() {
        super("Cost is not multiple of five");
    }
}

package com.machines.vending.domain.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super("Product not found exception");
    }
}

package com.machines.vending.domain.exceptions.product;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super("Product not found exception");
    }
}

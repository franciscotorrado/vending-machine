package com.machines.vending.domain.exceptions;

public class CreateProductWithGivenIdException extends Exception {
    public CreateProductWithGivenIdException() {
        super("Is not allowed to create a new product giving its id");
    }
}

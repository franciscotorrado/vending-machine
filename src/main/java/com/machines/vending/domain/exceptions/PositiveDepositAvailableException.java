package com.machines.vending.domain.exceptions;

public class PositiveDepositAvailableException extends Exception {
    public PositiveDepositAvailableException() {
        super("Error deleting your user account. Is not possible to delete an user with positive deposit. Please reset your deposit first.");
    }
}

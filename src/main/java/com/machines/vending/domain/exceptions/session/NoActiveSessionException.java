package com.machines.vending.domain.exceptions.session;

public class NoActiveSessionException extends Exception {
    public NoActiveSessionException() {
        super("There is no active session. Please login.");
    }
}

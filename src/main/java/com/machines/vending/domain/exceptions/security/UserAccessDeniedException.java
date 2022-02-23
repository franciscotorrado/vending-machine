package com.machines.vending.domain.exceptions.security;

public class UserAccessDeniedException extends Exception {
    public UserAccessDeniedException() {
        super("Your role is not allowed.");
    }
}

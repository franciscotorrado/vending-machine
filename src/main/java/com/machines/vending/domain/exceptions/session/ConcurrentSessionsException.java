package com.machines.vending.domain.exceptions.session;

public class ConcurrentSessionsException extends Exception {
    public ConcurrentSessionsException() {
        super("Another session is active. Please call /logout/all to terminate all active sessions and try login again.");
    }
}

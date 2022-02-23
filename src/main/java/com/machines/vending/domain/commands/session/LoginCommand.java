package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.security.InvalidUsernameOrPasswordException;
import com.machines.vending.domain.exceptions.session.ConcurrentSessionsException;

public interface LoginCommand {
    String execute(String username,
                   String password) throws InvalidUsernameOrPasswordException, ConcurrentSessionsException;
}

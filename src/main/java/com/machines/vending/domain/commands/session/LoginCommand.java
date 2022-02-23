package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.session.ConcurrentSessionsException;
import com.machines.vending.domain.exceptions.session.InvalidUsernameOrPasswordException;

public interface LoginCommand {
    String execute(String username,
                   String password) throws InvalidUsernameOrPasswordException, ConcurrentSessionsException;
}

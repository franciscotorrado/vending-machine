package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.session.ConcurrentSessionsException;
import com.machines.vending.domain.exceptions.session.InvalidUsernameOrPassword;

public interface LoginCommand {
    String login(String username,
                 String password) throws InvalidUsernameOrPassword, ConcurrentSessionsException;
}

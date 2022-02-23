package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.security.InvalidUsernameOrPasswordException;

public interface LogoutCommand {
    void execute(String username,
                 String password) throws InvalidUsernameOrPasswordException;
}

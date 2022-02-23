package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.session.ConcurrentSessionsException;
import com.machines.vending.domain.exceptions.session.InvalidUsernameOrPasswordException;

public interface LogoutCommand {
    void execute(final Integer userId) throws InvalidUsernameOrPasswordException, ConcurrentSessionsException;
}

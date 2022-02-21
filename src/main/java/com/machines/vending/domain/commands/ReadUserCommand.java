package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.UserNotFoundException;
import com.machines.vending.domain.models.User;

public interface ReadUserCommand {
    User execute(User user) throws UserNotFoundException;
}

package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.exceptions.user.UserNotFoundException;
import com.machines.vending.domain.models.User;

public interface ReadUserCommand {
    User execute(User user) throws UserNotFoundException;
}

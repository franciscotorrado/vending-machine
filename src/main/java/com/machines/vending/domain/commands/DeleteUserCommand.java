package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.User;

public interface DeleteUserCommand {
    void execute(User user);
}

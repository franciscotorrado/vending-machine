package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.CreateUserWithGivenIdException;
import com.machines.vending.domain.exceptions.InvalidPasswordException;
import com.machines.vending.domain.exceptions.InvalidRoleException;
import com.machines.vending.domain.exceptions.InvalidUsernameException;
import com.machines.vending.domain.models.User;

public interface CreateUserCommand {
    User execute(User user) throws InvalidPasswordException, InvalidUsernameException, InvalidRoleException, CreateUserWithGivenIdException;
}

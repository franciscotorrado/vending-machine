package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.User;
import com.machines.vending.infraestructure.persistence.entities.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteUserCommandImpl implements DeleteUserCommand {
    private final UserRepository userRepository;

    @Override
    public void execute(final User user) {
        userRepository.deleteById(user.getId());
    }
}

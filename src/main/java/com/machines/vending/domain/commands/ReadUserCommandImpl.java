package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.UserNotFoundException;
import com.machines.vending.domain.models.User;
import com.machines.vending.infraestructure.persistence.entities.UserMapper;
import com.machines.vending.infraestructure.persistence.entities.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadUserCommandImpl implements ReadUserCommand {
    private final UserRepository userRepository;

    @Override
    public User execute(final User user) throws UserNotFoundException {
        return UserMapper
                .fromEntity(userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new))
                .toModel();
    }
}

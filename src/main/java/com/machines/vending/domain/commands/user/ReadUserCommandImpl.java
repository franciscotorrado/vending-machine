package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.exceptions.user.UserNotFoundException;
import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.mappers.UserMapper;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReadUserCommandImpl implements ReadUserCommand {
    private final UserRepository userRepository;

    @Override
    public User execute(final User user) throws UserNotFoundException {
        return UserMapper
                .fromEntity(userRepository.findByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new))
                .toModel();
    }
}

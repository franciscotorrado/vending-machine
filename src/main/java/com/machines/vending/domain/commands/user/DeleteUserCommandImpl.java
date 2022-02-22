package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserCommandImpl implements DeleteUserCommand {
    private final UserRepository userRepository;

    @Override
    public void execute(final User user) {
        // TODO Check deposit
        userRepository.deleteById(user.getId());
    }
}

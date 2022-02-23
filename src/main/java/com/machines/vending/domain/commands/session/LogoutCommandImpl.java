package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.security.InvalidUsernameOrPasswordException;
import com.machines.vending.infrastructure.persistence.entities.UserEntity;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import com.machines.vending.infrastructure.session.TokenServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LogoutCommandImpl implements LogoutCommand {
    private final UserRepository userRepository;

    @Override
    public void execute(final String username,
                          final String password) throws InvalidUsernameOrPasswordException {
        final UserEntity user = userRepository
                .findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(InvalidUsernameOrPasswordException::new);

        TokenServer.removeToken(user.getId());
    }
}

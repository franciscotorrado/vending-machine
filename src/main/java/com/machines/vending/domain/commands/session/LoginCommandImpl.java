package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.security.InvalidUsernameOrPasswordException;
import com.machines.vending.domain.exceptions.session.ConcurrentSessionsException;
import com.machines.vending.domain.models.Role;
import com.machines.vending.infrastructure.persistence.entities.UserEntity;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import com.machines.vending.infrastructure.session.TokenServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginCommandImpl implements LoginCommand {
    private final UserRepository userRepository;

    @Override
    public String execute(final String username,
                                      final String password) throws InvalidUsernameOrPasswordException, ConcurrentSessionsException {
        final UserEntity user = userRepository
                .findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(InvalidUsernameOrPasswordException::new);

        return TokenServer.getToken(user.getId(), Role.of(user.getRole()))
                .orElseThrow(ConcurrentSessionsException::new);
    }
}

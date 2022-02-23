package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.session.ConcurrentSessionsException;
import com.machines.vending.domain.exceptions.session.InvalidUsernameOrPassword;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import com.machines.vending.infrastructure.session.TokenServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginCommandImpl implements LoginCommand {
    private final UserRepository userRepository;

    @Override
    public String login(final String username,
                        final String password) throws InvalidUsernameOrPassword, ConcurrentSessionsException {
        final Integer userId = userRepository
                .findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(InvalidUsernameOrPassword::new).getId();

        return TokenServer.getToken(userId)
                .orElseThrow(ConcurrentSessionsException::new);
    }
}

package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.session.ConcurrentSessionsException;
import com.machines.vending.domain.exceptions.session.InvalidUsernameOrPasswordException;
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
        final Integer userId = userRepository
                .findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(InvalidUsernameOrPasswordException::new).getId();

        return TokenServer.getToken(userId)
                .orElseThrow(ConcurrentSessionsException::new);
    }
}

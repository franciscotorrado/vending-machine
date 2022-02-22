package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.session.InvalidUsernameOrPassword;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import com.machines.vending.infrastructure.persistence.session.TokenServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LogoutCommandImpl implements LogoutCommand {
    private final UserRepository userRepository;

    @Override
    public void logout(final String username,
                       final String password) throws InvalidUsernameOrPassword {
        final Integer userId = userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(InvalidUsernameOrPassword::new)
                .getId();

        TokenServer.removeToken(userId);
    }
}

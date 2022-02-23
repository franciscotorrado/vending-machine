package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.session.InvalidUsernameOrPasswordException;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import com.machines.vending.infrastructure.session.TokenServer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class LogoutCommandImpl implements LogoutCommand {
    private final UserRepository userRepository;

    @Override
    public void execute(final Integer userId) throws InvalidUsernameOrPasswordException {
        if(Objects.isNull(userId)) return;
        TokenServer.removeToken(userId);
    }
}

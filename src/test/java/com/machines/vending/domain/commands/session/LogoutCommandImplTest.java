package com.machines.vending.domain.commands.session;

import com.machines.vending.domain.exceptions.security.InvalidUsernameOrPasswordException;
import com.machines.vending.infrastructure.persistence.entities.UserEntity;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogoutCommandImplTest {

    private LogoutCommand logoutCommand;
    @Mock
    private UserRepository userRepository;
    private String username;
    private String password;

    @BeforeEach
    void setUp() {
        logoutCommand = new LogoutCommandImpl(userRepository);
        username = "Peter10";
        password = "sdf443#fdTT";
    }

    @Test
    void execute() throws InvalidUsernameOrPasswordException {
        //given
        final UserEntity userEntity = UserEntity.builder().username(username).password(password).build();
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(userEntity));
        //when
        //then
        logoutCommand.execute(username, password);
    }

    @Test
    void shouldThrowInvalidUsernameOrPasswordException() {
        //given
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        //when
        Assertions.assertThrows(InvalidUsernameOrPasswordException.class, () -> logoutCommand.execute(username, password));
        //then
    }
}

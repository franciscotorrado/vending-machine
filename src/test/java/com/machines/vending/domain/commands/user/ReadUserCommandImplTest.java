package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.exceptions.user.UserNotFoundException;
import com.machines.vending.domain.models.Role;
import com.machines.vending.domain.models.User;
import com.machines.vending.infraestructure.persistence.entities.UserEntity;
import com.machines.vending.infraestructure.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadUserCommandImplTest {

    private ReadUserCommand readUserCommand;

    @Mock
    private UserRepository userRepository;

    private int id;
    private String username;
    private String password;
    private String role;

    @BeforeEach
    void setUp() {
        readUserCommand = new ReadUserCommandImpl(userRepository);
        id = new Random().nextInt();
        username = "Lucas Head";
        password = "er$4ttGH33";
        role = Role.BUYER.name();
    }

    @Test
    void readUser() throws UserNotFoundException {
        //given
        final User userToRead = User.builder().id(id).build();
        when(userRepository.findById(id)).thenReturn(Optional.of(UserEntity.builder().id(id).username(username)
                .password(password).role(role).build()));

        //when
        final User storedUser = readUserCommand.execute(userToRead);

        //then
        assertThat(storedUser.getId()).isEqualTo(id);
        assertThat(storedUser.getUsername()).isEqualTo(username);
        assertThat(storedUser.getPassword()).isEqualTo(password);
        assertThat(storedUser.getRole()).isEqualTo(role);
    }

    @Test
    void shouldThrowsUserNotFoundException_whenUserNotExists() {
        // given
        final User userToUpdate = User.builder().id(id).build();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // when
        // then
        assertThrows(UserNotFoundException.class, () -> readUserCommand.execute(userToUpdate));
    }
}

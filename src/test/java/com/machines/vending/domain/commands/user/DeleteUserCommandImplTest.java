package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteUserCommandImplTest {

    private DeleteUserCommand deleteUserCommand;

    @Mock
    private UserRepository userRepository;

    private int id;

    @BeforeEach
    void setUp() {
        deleteUserCommand = new DeleteUserCommandImpl(userRepository);
        id = new Random().nextInt();
    }

    @Test
    void deleteUser() {
        //given
        final User userToDelete = User.builder().id(id).build();

        //when
        deleteUserCommand.execute(userToDelete);

        //then
        verify(userRepository).deleteById(id);
    }
}

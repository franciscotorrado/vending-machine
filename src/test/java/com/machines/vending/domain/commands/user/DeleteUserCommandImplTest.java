package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.commands.deposit.ReadDepositCommand;
import com.machines.vending.domain.exceptions.PositiveDepositAvailableException;
import com.machines.vending.domain.models.DepositInfo;
import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.ZERO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteUserCommandImplTest {

    private DeleteUserCommand deleteUserCommand;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ReadDepositCommand readDepositCommand;

    private int id;
    private String username;
    private String password;

    @BeforeEach
    void setUp() {
        deleteUserCommand = new DeleteUserCommandImpl(userRepository, readDepositCommand);
        id = new Random().nextInt();
        username = "client";
        password = "dsfser$4TT";
    }

    @Test
    void deleteUser() throws PositiveDepositAvailableException {
        //given
        final User userToDelete = User.builder().id(id).username(username).password(password).build();
        when(readDepositCommand.read(any())).thenReturn(DepositInfo.builder().availableAmount(ZERO).build());

        //when
        deleteUserCommand.execute(userToDelete);

        //then
        verify(userRepository).deleteByIdAndUsernameAndPassword(id, username, password);
    }

    @Test
    void errorWhenUserDepositIsPositive() {
        //given
        final User userToDelete = User.builder().id(id).username(username).password(password).build();
        when(readDepositCommand.read(any())).thenReturn(DepositInfo.builder().availableAmount(TEN).build());

        //when
        //then
        assertThrows(PositiveDepositAvailableException.class, () -> deleteUserCommand.execute(userToDelete));
    }
}

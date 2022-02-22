package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.exceptions.role.InvalidRoleException;
import com.machines.vending.domain.exceptions.user.CreateUserWithGivenIdException;
import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.entities.UserEntity;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static com.machines.vending.domain.models.Role.BUYER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserCommandImplTest {

    private CreateUserCommand createUserCommand;

    @Mock
    private UserRepository userRepository;

    private Integer id;
    private String username;
    private String password;
    private String role;

    @BeforeEach
    void setUp() {
        createUserCommand = new CreateUserCommandImpl(userRepository);
        id = new Random().nextInt();
        username = "JohnRipple";
        password = "fdEr35v#erwf";
        role = BUYER.name();
    }

    @Test
    void createUser() throws InvalidPasswordException, InvalidRoleException, InvalidUsernameException, CreateUserWithGivenIdException {
        //given
        final User userToCreate = User.builder().username(username).password(password).role(role).build();
        when(userRepository.save(any())).thenReturn(UserEntity.builder().id(id).build());

        //when
        final User createdUser = createUserCommand.execute(userToCreate);

        //then
        assertThat(createdUser.getId()).isEqualTo(id);
        final ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userEntityCaptor.capture());
        final UserEntity userEntity = userEntityCaptor.getValue();
        assertThat(userEntity.getId()).isNull();
        assertThat(userEntity.getUsername()).isEqualTo(username);
        assertThat(userEntity.getPassword()).isEqualTo(password);
        assertThat(userEntity.getRole()).isEqualTo(role);
    }

    @Test
    void shouldThrowsNotValidPasswordException_whenPasswordNotMeetRequirements() {
        // given
        final User userToCreate = User.builder().username(username).password("abc").role(role).build();

        // when
        // then
        assertThrows(InvalidPasswordException.class, () -> createUserCommand.execute(userToCreate));
    }

    @Test
    void shouldThrowsNotValidUsernameException_whenUsernameNotMeetRequirements() {
        // given
        // when
        final User userToCreate = User.builder().username("aaa").password(password).build();

        // then
        assertThrows(InvalidUsernameException.class, () -> createUserCommand.execute(userToCreate));
    }

    @Test
    void shouldThrowsNotValidRoleException_whenReceivedRoleIsNotValid() {
        // given
        // when
        final User userToCreate = User.builder().username(username).password(password).role("UNKNOWN").build();

        // then
        assertThrows(InvalidRoleException.class, () -> createUserCommand.execute(userToCreate));
    }

    @Test
    void shouldThrowsCreateUserWithGivenIdException_whenReceivedUserInformationIncludesNotNullId() {
        // given
        // when
        final User userToCreate = User.builder().id(2).username(username).password(password).role("UNKNOWN").build();

        // then
        assertThrows(CreateUserWithGivenIdException.class, () -> createUserCommand.execute(userToCreate));
    }
}

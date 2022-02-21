package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    private int id;
    private String username;
    private String password;
    private String role;

    @BeforeEach
    void setUp() {
        id = new Random().nextInt();
        username = "AnaBrown_28";
        password = "er4#TR35cv$";
        role = Role.SELLER.name();
    }

    @Test
    void shouldValidateUser() {
        // given
        final var userBuilder = User.builder().id(id).username(username).password(password).role(role);

        // when
        final var user = userBuilder.build();

        // then
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getRole()).isEqualTo(role);
    }

    @Test
    void shouldThrowsInvalidUsernameException_whenUsernameNotMeetRequirement() {
        // given
        // when
        final var user = User.builder().username("aaa").build();

        // then
        assertThrows(InvalidUsernameException.class, () -> User.validate(user));
    }

    @Test
    void shouldThrowsInvalidPasswordException_whenPasswordNotMeetRequirement() {
        // given
        // when
        final var user = User.builder().username(username).password("asd").build();

        // then
        assertThrows(InvalidPasswordException.class, () -> User.validate(user));
    }
}

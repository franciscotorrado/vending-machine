package com.machines.vending.domain.exceptions.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InvalidUsernameOrPasswordExceptionTest {
    @Test
    void constructor() {
        // given
        InvalidUsernameOrPasswordException exception = new InvalidUsernameOrPasswordException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Invalid username or password. Try again!");
    }
}

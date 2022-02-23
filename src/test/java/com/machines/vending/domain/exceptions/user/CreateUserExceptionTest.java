package com.machines.vending.domain.exceptions.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateUserExceptionTest {
    @Test
    void constructor() {
        // given
        CreateUserException exception = new CreateUserException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("An error happened. Please try again or try with another username.");
    }
}

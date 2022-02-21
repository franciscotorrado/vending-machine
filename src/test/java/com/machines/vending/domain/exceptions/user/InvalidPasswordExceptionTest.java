package com.machines.vending.domain.exceptions.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidPasswordExceptionTest {
    @Test
    void constructor() {
        // given
        InvalidPasswordException exception = new InvalidPasswordException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Password is not valid. " +
                "It is must be more than 8 and less than 20 characters long, " +
                "contain uppercase and lowercase letters, numbers " +
                "and at least one special character of '#$%@_-'");
    }
}

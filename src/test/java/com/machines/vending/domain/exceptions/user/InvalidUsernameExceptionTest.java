package com.machines.vending.domain.exceptions.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidUsernameExceptionTest {
    @Test
    void constructor() {
        // given
        InvalidUsernameException exception = new InvalidUsernameException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Username is not valid. " +
                "It is must be between 6 and 12 characters long, " +
                "contain uppercase or lowercase letters, numbers and _ " +
                "but no spaces or other special characters");
    }
}

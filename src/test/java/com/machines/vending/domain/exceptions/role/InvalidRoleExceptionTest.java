package com.machines.vending.domain.exceptions.role;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidRoleExceptionTest {
    @Test
    void constructor() {
        // given
        InvalidRoleException exception = new InvalidRoleException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Provided role is not valid");
    }
}

package com.machines.vending.domain.exceptions.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAccessDeniedExceptionTest {
    @Test
    void constructor() {
        // given
        UserAccessDeniedException exception = new UserAccessDeniedException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Your role is not allowed.");
    }
}

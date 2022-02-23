package com.machines.vending.domain.exceptions.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoActiveSessionExceptionTest {
    @Test
    void constructor() {
        // given
        NoActiveSessionException exception = new NoActiveSessionException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("There is no active session. Please login.");
    }
}

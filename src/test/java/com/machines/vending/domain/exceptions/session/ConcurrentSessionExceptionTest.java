package com.machines.vending.domain.exceptions.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConcurrentSessionExceptionTest {
    @Test
    void constructor() {
        // given
        ConcurrentSessionsException exception = new ConcurrentSessionsException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Another session is active. Please call /logout/all to terminate all active sessions and try login again.");
    }
}

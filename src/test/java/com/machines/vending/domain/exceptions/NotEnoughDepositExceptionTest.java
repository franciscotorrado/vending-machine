package com.machines.vending.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotEnoughDepositExceptionTest {
    @Test
    void constructor() {
        // given
        NotEnoughDepositException exception = new NotEnoughDepositException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("No enough deposit available");
    }
}

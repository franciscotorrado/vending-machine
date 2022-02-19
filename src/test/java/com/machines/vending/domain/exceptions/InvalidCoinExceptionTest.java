package com.machines.vending.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidCoinExceptionTest {
    @Test
    void constructor() {
        // given
        InvalidCoinException exception = new InvalidCoinException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Invalid coin received");
    }
}

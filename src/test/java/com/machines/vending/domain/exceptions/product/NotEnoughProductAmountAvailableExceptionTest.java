package com.machines.vending.domain.exceptions.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotEnoughProductAmountAvailableExceptionTest {
    @Test
    void constructor() {
        // given
        NotEnoughProductAmountAvailableException exception = new NotEnoughProductAmountAvailableException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Available amount is not enough");
    }
}

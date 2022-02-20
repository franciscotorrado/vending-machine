package com.machines.vending.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotValidProductCostExceptionTest {
    @Test
    void constructor() {
        // given
        NotValidProductCostException exception = new NotValidProductCostException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Cost is not multiple of five");
    }
}

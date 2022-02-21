package com.machines.vending.domain.exceptions.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotValidProductNameExceptionTest {
    @Test
    void constructor() {
        // given
        NotValidProductNameException exception = new NotValidProductNameException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Empty name is not valid");
    }
}

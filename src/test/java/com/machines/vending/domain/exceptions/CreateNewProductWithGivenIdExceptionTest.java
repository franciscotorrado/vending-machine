package com.machines.vending.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateNewProductWithGivenIdExceptionTest {
    @Test
    void constructor() {
        // given
        CreateProductWithGivenIdException exception = new CreateProductWithGivenIdException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Is not allowed to create a new product giving its id");
    }
}

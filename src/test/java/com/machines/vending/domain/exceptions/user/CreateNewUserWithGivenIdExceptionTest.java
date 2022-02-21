package com.machines.vending.domain.exceptions.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateNewUserWithGivenIdExceptionTest {
    @Test
    void constructor() {
        // given
        CreateUserWithGivenIdException exception = new CreateUserWithGivenIdException();
        // when
        // then
        assertThat(exception.getMessage()).isEqualTo("Is not allowed to create a new user giving its id");
    }
}

package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.InvalidRoleException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoleTest {

    @Test
    void getEnumByName() {
        assertThat(Role.of("seller")).isEqualTo(Role.SELLER);
        assertThat(Role.of("buyer")).isEqualTo(Role.BUYER);
    }

    @Test
    void shouldThrowInvalidCoinException_whenAnInvalidCoinIsReceived() {
        // given
        final String role = "admin";

        // when
        // then
        assertThrows(InvalidRoleException.class, () -> Role.validate(role));
    }
}

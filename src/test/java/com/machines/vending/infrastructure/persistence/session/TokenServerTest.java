package com.machines.vending.infrastructure.persistence.session;

import com.machines.vending.domain.models.Role;
import com.machines.vending.infrastructure.session.TokenServer;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class TokenServerTest {

    @Test
    void getToken() {
        //given
        final Integer userId = new Random().nextInt();
        final Role role = Role.BUYER;
        //when
        final Optional<String> optionalOfToken = TokenServer.getToken(userId, role);
        //then
        assertThat(optionalOfToken).isPresent();
        assertThat(optionalOfToken.get().length()).isEqualTo(44);
    }

    @Test
    void notReplaceCurrentTokenExist() {
        //given
        final Integer userId = new Random().nextInt();
        final Role role = Role.BUYER;
        TokenServer.getToken(userId, role);
        //when
        final Optional<String> newToken = TokenServer.getToken(userId, role);
        //then
        assertThat(newToken).isEmpty();
    }

    @Test
    void removeToken() {
        //given
        final int userId = new Random().nextInt();
        final Role role = Role.BUYER;
        final Optional<String> optionalOfToken = TokenServer.getToken(userId, role);
        final String token = optionalOfToken.get();
        //when
        TokenServer.removeToken(userId);
        //then
        assertThat(TokenServer.getUserSessionDetails(token)).isEmpty();
    }
}

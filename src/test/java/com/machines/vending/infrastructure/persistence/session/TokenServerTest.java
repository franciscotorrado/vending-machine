package com.machines.vending.infrastructure.persistence.session;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class TokenServerTest {

    @Test
    void getToken() {
        //given
        final Integer userId = new Random().nextInt();
        //when
        final Optional<String> optionalOfToken = TokenServer.getToken(userId);
        //then
        assertThat(optionalOfToken).isPresent();
        final String token = optionalOfToken.get();
        assertThat(token.length()).isEqualTo(44);
        assertThat(TokenServer.getUserId(token).get()).isEqualTo(userId);
    }

    @Test
    void notReplaceCurrentTokenExist() {
        //given
        final Integer userId = new Random().nextInt();
        TokenServer.getToken(userId);
        //when
        final Optional<String> newToken = TokenServer.getToken(userId);
        //then
        assertThat(newToken).isEmpty();
    }

    @Test
    void removeToken() {
        //given
        final int userId = new Random().nextInt();
        final Optional<String> token = TokenServer.getToken(userId);
        //when
        TokenServer.removeToken(userId);
        //then
        assertThat(TokenServer.getUserId(token.get())).isEmpty();
    }
}

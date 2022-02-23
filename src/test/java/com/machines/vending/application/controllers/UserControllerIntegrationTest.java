package com.machines.vending.application.controllers;

import com.machines.vending.domain.models.Role;
import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.entities.UserEntity;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIntegrationTest extends AuthenticationBaseTest {

    @MockBean
    public UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(
                        UserEntity.builder().id(userId).username(username)
                                .password(password).build()));

        when(userRepository.save(any()))
                .thenReturn(UserEntity.builder().id(userId).username(username)
                        .password(password).build());
    }

    @Test
    void shouldCreateUser() throws Exception {
        // given
        final String url = "/user";
        final User userToCreate = User.builder().username(username).password(password).role(Role.BUYER.name()).build();
        final String user = objectMapper.writeValueAsString(userToCreate);
        // when
        String response = mockMvc.perform(
                        post(url)
                                .contentType(APPLICATION_JSON)
                                .content(user))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        assertThat(response).isEmpty();
    }

    @Test
    void shouldDeleteUser() throws Exception {
        // given
        final String url = "/user";
        final User userToCreate = User.builder().username(username).password(password).role(Role.BUYER.name()).build();
        final String user = objectMapper.writeValueAsString(userToCreate);
        // when
        String response = mockMvc.perform(
                        delete(url)
                                .header("Authorization", authToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        assertThat(response).isEmpty();
    }

}

package com.machines.vending.application.controllers;

import com.machines.vending.domain.models.Role;
import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.entities.UserEntity;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIntegrationTest extends AuthenticationBaseTest {

    @Autowired
    public UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
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
        userRepository.save(UserEntity.builder().username(username).password(password).role(Role.BUYER.name()).build());
        final String url = "/user";
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

    @Test
    void shouldUpdateUser() throws Exception {
        // given
        userRepository.save(UserEntity.builder().username(username).password(password).role(Role.BUYER.name()).build());
        final String url = "/user";
        final User userToUpdate = User.builder().username(username).password(password + "v2").build();
        final String user = objectMapper.writeValueAsString(userToUpdate);
        // when
        String response = mockMvc.perform(
                        put(url)
                                .contentType(APPLICATION_JSON)
                                .content(user)
                                .header("Authorization", authToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        assertThat(response).isEmpty();
    }

    @Test
    void shouldReadUser() throws Exception {
        // given
        userRepository.save(UserEntity.builder().username(username).password(password).role(Role.BUYER.name()).build());
        final String url = "/user";
        // when
        String response = mockMvc.perform(
                        get(url)
                                .contentType(APPLICATION_JSON)
                                .header("Authorization", authToken()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        assertThat(response).isNotEmpty();
    }

}

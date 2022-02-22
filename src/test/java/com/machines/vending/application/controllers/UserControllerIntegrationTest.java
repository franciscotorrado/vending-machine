package com.machines.vending.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machines.vending.Main;
import com.machines.vending.domain.models.Role;
import com.machines.vending.domain.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateUser() throws Exception {
        // given
        final String url = "/user";
        final User userToCreate = User.builder().username("BenTom").password("erwe343$E").role(Role.BUYER.name()).build();
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
        final User userToCreate = User.builder().username("BenTom").password("erwe343$E").role(Role.BUYER.name()).build();
        final String user = objectMapper.writeValueAsString(userToCreate);
        // when
        String response = mockMvc.perform(
                        delete(url))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        assertThat(response).isEmpty();
    }
}

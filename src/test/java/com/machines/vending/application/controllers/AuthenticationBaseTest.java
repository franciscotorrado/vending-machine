package com.machines.vending.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machines.vending.Main;
import com.machines.vending.domain.models.security.AuthenticationRequest;
import com.machines.vending.domain.models.security.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AuthenticationBaseTest {

    protected static Integer userId = new Random().nextInt();
    protected static String username = "User1234";
    protected static String password = "rwefw3#EEc";
    protected final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public MockMvc mockMvc;

    protected String authToken() throws Exception {
        final AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);
        return objectMapper.readValue(mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), AuthenticationResponse.class).getToken();
    }
}

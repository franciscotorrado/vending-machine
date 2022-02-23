package com.machines.vending.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machines.vending.Main;
import com.machines.vending.domain.models.security.LoginRequest;
import com.machines.vending.domain.models.security.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AuthenticationBaseTest {

    protected static String username = "User1234";
    protected static String password = "rwefw3#EEc";
    protected final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public MockMvc mockMvc;

    protected String authToken() throws Exception {
        final LoginRequest loginRequest = new LoginRequest(username, password);
        return objectMapper.readValue(mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), LoginResponse.class).getToken();
    }
}

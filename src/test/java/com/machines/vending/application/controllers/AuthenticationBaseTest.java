package com.machines.vending.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machines.vending.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
        return mockMvc.perform(
                        post("/login")
                                .param("username", username)
                                .param("password", password))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}

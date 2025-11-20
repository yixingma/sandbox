package com.matcha.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProtectedController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProtectedControllerTest {

    private static final String HELLO_ENDPOINT = "/api/protected/hello";
    private static final String HELLO_RESPONSE = "Hello, Spring Boot!";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHelloEndpoint() throws Exception {
        mockMvc.perform(get(HELLO_ENDPOINT))
                .andExpectAll(
                        status().isOk(),
                        content().string(HELLO_RESPONSE),
                        content().contentType("text/plain;charset=UTF-8"));
    }
}

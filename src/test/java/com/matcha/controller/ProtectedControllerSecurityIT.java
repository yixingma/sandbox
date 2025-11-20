package com.matcha.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProtectedControllerSecurityIT {

    private static final String PROTECTED_ENDPOINT = "/api/protected/hello";
    private static final String PUBLIC_ENDPOINT = "/api/public/ping";
    private static final String CLIENT_ID = System.getenv().getOrDefault("AZURE_CLIENT_ID", "your-client-id");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void protectedEndpointRequiresAuthentication() throws Exception {
        mockMvc.perform(get(PROTECTED_ENDPOINT))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void protectedEndpointAllowsValidJwt() throws Exception {
        mockMvc.perform(get(PROTECTED_ENDPOINT)
                .with(jwt().jwt(jwt -> jwt.claim("aud", CLIENT_ID))))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Spring Boot!"));
    }

    @Test
    void publicEndpointIsAccessibleWithoutAuth() throws Exception {
        mockMvc.perform(get(PUBLIC_ENDPOINT).accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("pong"));
    }
}

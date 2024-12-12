package com.spring.app.controller.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.app.dto.LoginDTO;
import com.spring.app.mocks.entities.UserMock;
import com.spring.app.mocks.mvc.MvcResultMock;
import com.spring.app.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private LoginDTO loginDTO;
    
    @BeforeEach 
    void setup() {
        given(userRepository.findByUsername("test")).willReturn(Optional.of(UserMock.getUser()));

        loginDTO = new LoginDTO();
        loginDTO.setUsername("test");
        loginDTO.setPassword("test");
    }

    @Test
    void testLogin() throws Throwable {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/auth/login");
        builder.content(objectMapper.writeValueAsString(loginDTO));
        builder.contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = MvcResultMock.getResponse(mockMvc, builder);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}

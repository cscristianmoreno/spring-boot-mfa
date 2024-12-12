package com.spring.app.controller.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.app.annotation.MockUser;
import com.spring.app.entity.Users;
import com.spring.app.mocks.entities.UserMock;
import com.spring.app.mocks.mvc.MvcResultMock;

import jakarta.servlet.http.HttpServletResponse;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Users users;

    @BeforeEach
    void setup() {
        users = UserMock.getUser();
    }

    @Test
    @Order(1)
    void testSave() throws Throwable {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/save")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(users));

        HttpServletResponse response = MvcResultMock.getResponse(mockMvc, builder);
        
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @Order(2)
    @MockUser
    void testUpdate() throws Throwable {
        users.setId(1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/users/update")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(users));

        HttpServletResponse response = MvcResultMock.getResponse(mockMvc, builder);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @MockUser
    @Order(3)
    void testFindById() throws Throwable {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/{id}", 1);

        HttpServletResponse response = MvcResultMock.getResponse(mockMvc, builder);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @MockUser
    @Order(4)
    void testDeleteById() throws Throwable {
        users.setId(1);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/users/{id}", users.getId());

        HttpServletResponse response = MvcResultMock.getResponse(mockMvc, builder);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}

package com.spring.app.mocks.mvc;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public abstract class MvcResultMock {
    
    public static MockHttpServletResponse getResponse(final MockMvc mockMvc, final MockHttpServletRequestBuilder builder) throws Throwable {
        ResultActions resultActions = mockMvc.perform(builder);
        MvcResult result = resultActions.andReturn();
        MockHttpServletResponse response = result.getResponse();
        return response;
    }
}

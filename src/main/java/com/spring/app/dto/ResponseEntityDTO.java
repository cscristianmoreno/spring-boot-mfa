package com.spring.app.dto;

import org.springframework.http.HttpStatus;

public class ResponseEntityDTO {
    private Object value;
    private HttpStatus status;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public HttpStatus getStatus() {
        return status;
    }
    
    public void setStatus(HttpStatus status) {
        this.status = status;
    }    
}

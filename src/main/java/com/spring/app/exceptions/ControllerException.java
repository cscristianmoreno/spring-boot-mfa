package com.spring.app.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.app.dto.ResponseEntityDTO;
import com.spring.app.utils.ResponseEntityUtil;

@ControllerAdvice
public class ControllerException {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseEntityDTO> exceptionHandler(Exception exception) {
        return ResponseEntityUtil.unauthorize(exception.getMessage());
    }
}

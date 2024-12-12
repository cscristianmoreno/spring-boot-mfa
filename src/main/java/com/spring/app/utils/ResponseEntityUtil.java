package com.spring.app.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring.app.dto.ResponseEntityDTO;

public abstract class ResponseEntityUtil<T> {
    
    private static HttpStatus status;
    private static ResponseEntityDTO responseEntityDTO;

    public static ResponseEntity<ResponseEntityDTO> success(final Object object) {
        status = HttpStatus.OK;

        responseEntityDTO = new ResponseEntityDTO();
        responseEntityDTO.setValue(object);
        responseEntityDTO.setStatus(status);

        return response(responseEntityDTO);
    }

    public static ResponseEntity<ResponseEntityDTO> unauthorize(final Object object) {
        status = HttpStatus.UNAUTHORIZED;

        responseEntityDTO = new ResponseEntityDTO();
        responseEntityDTO.setValue(object);
        responseEntityDTO.setStatus(status);

        return response(responseEntityDTO);
    }

    private static ResponseEntity<ResponseEntityDTO> response(final ResponseEntityDTO responseEntityDTO) {
        return new ResponseEntity<ResponseEntityDTO>(responseEntityDTO, status);
    }
}

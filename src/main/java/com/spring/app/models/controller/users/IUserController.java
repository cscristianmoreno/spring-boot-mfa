package com.spring.app.models.controller.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.app.entity.Users;
import com.spring.app.models.controller.IController;

import com.spring.app.dto.ChangePasswordDTO;
import com.spring.app.dto.ResponseEntityDTO;

public interface IUserController extends IController<Users> {
    
    @PutMapping("/update-password")
    ResponseEntity<ResponseEntityDTO> changePassword(@RequestBody ChangePasswordDTO password);
}

package com.spring.app.controller.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.app.dto.ChangePasswordDTO;
import com.spring.app.dto.ResponseEntityDTO;
import com.spring.app.entity.Users;

import com.spring.app.models.controller.users.IUserController;
import com.spring.app.services.UserRepositoryService;
import com.spring.app.utils.ResponseEntityUtil;

@Controller
@ResponseBody
@RequestMapping("/users")
public class UsersController implements IUserController {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Override
    public ResponseEntity<ResponseEntityDTO> save(Users entity) {
        Users result = userRepositoryService.save(entity);
        return ResponseEntityUtil.success(result);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> update(Users entity) {
        Users result = userRepositoryService.update(entity);
        return ResponseEntityUtil.success(result);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> findById(int id) {
        Optional<Users> result =  userRepositoryService.findById(id);
        return ResponseEntityUtil.success(result.get());
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> deleteById(int id) {
        userRepositoryService.deleteById(id);
        String message = String.format("User %d has been deleted", id);
        return ResponseEntityUtil.success(message);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> changePassword(ChangePasswordDTO password) {
        return ResponseEntityUtil.success("asd");
    }
}

package com.github.alexcastro1919.simpleloginbackend.web.controller.user;

import com.github.alexcastro1919.simpleloginbackend.domain.dto.UserDTO;
import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.key.UserKey;
import com.github.alexcastro1919.simpleloginbackend.persistence.mapper.UserMapper;
import com.github.alexcastro1919.simpleloginbackend.persistence.service.user.UserService;
import com.github.alexcastro1919.simpleloginbackend.web.security.user.UserSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserSecurityManager userSecurityManager;

    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUser(@RequestBody UserKey key) {
        if (!userSecurityManager.isValid(key)) {
            return ResponseEntity.status(401).build();
        }

        UserDTO userDTO = userMapper.toUserDTO(userService.getById(key.getBaseId()));
        return ResponseEntity.ok(userDTO);
    }
}

package com.github.alexcastro1919.simpleloginbackend.web.controller.user;

import com.github.alexcastro1919.simpleloginbackend.domain.dto.UserDTO;
import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.key.UserKey;
import com.github.alexcastro1919.simpleloginbackend.persistence.mapper.UserMapper;
import com.github.alexcastro1919.simpleloginbackend.persistence.service.user.UserService;
import com.github.alexcastro1919.simpleloginbackend.web.security.user.UserSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para obtener el usuarios a partir de un key y un id.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserSecurityManager userSecurityManager;


    @CrossOrigin(origins = "http://192.168.5.4:5500")
    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUser(String key, String baseId) {
        UserKey userKey = new UserKey(key, baseId);
        if (!userSecurityManager.isValid(userKey)) {
            return ResponseEntity.status(401).build();
        }

        UserDTO userDTO = userMapper.toUserDTO(userService.getById(userKey.getBaseId()));
        return ResponseEntity.ok(userDTO);
    }
}

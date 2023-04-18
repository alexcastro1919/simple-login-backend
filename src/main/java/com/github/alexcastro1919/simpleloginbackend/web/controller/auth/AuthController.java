package com.github.alexcastro1919.simpleloginbackend.web.controller.auth;

import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.User;
import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.key.UserKey;
import com.github.alexcastro1919.simpleloginbackend.persistence.mapper.UserMapper;
import com.github.alexcastro1919.simpleloginbackend.persistence.service.user.UserAuthResult;
import com.github.alexcastro1919.simpleloginbackend.persistence.service.user.UserService;
import com.github.alexcastro1919.simpleloginbackend.web.security.user.UserAuthUtil;
import com.github.alexcastro1919.simpleloginbackend.web.security.user.UserSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAuthUtil userAuthUtil;
    @Autowired
    private UserSecurityManager userSecurityManager;

    @PostMapping("/login")
    public ResponseEntity<UserKey> login(String email, String password) {
        UserAuthResult result = userService.auth(email, password);

        switch (result) {
            case SUCCESS -> {
                User user = userService.getByEmail(email);
                UserKey userKey = new UserKey(user.getId());
                userSecurityManager.insertUser(userKey);
                return ResponseEntity.ok(userKey);
            }
            case UNKNOWN_USER, WRONG_PASSWORD -> {
                return ResponseEntity.status(401).build();
            }
        }

        return ResponseEntity.status(500).build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserKey> register(String email, String password, String name, String lastName) {
        if (userService.getByEmail(email) != null) {
            return ResponseEntity.status(409).build();
        }

        password = userAuthUtil.encodePassword(password);
        User user = new User(email, password, name, lastName);
        userService.create(user);

        UserKey userKey = new UserKey(user.getId());
        userSecurityManager.insertUser(userKey);
        return ResponseEntity.ok(userKey);
    }
}
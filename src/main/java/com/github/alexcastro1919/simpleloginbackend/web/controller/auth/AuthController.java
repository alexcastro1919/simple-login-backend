package com.github.alexcastro1919.simpleloginbackend.web.controller.auth;

import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.User;
import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.key.UserKey;
import com.github.alexcastro1919.simpleloginbackend.persistence.mapper.UserMapper;
import com.github.alexcastro1919.simpleloginbackend.persistence.service.user.UserAuthResult;
import com.github.alexcastro1919.simpleloginbackend.persistence.service.user.UserService;
import com.github.alexcastro1919.simpleloginbackend.web.payload.auth.LoginRequest;
import com.github.alexcastro1919.simpleloginbackend.web.payload.auth.RegisterRequest;
import com.github.alexcastro1919.simpleloginbackend.web.security.user.UserAuthUtil;
import com.github.alexcastro1919.simpleloginbackend.web.security.user.UserSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller para trabajar con el login y el register de los usuarios.
 */
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

    @CrossOrigin(origins = "http://192.168.5.4:5500")
    @PostMapping("/login")
    public ResponseEntity<UserKey> login(@RequestBody LoginRequest request) {
        String email = request.email();
        String password = request.password();
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

    @CrossOrigin(origins = "http://192.168.5.4:5500")
    @PostMapping(path = "/register")
    public ResponseEntity<UserKey> register(@RequestBody RegisterRequest request) {
        if (userService.getByEmail(request.getEmail()) != null) {
            return ResponseEntity.status(409).build();
        }

        request.setPassword(userAuthUtil.encodePassword(request.getPassword()));
        User user = new User(request.getEmail(), request.getPassword(), request.getName(), request.getLastName());
        userService.create(user);

        UserKey userKey = new UserKey(user.getId());
        userSecurityManager.insertUser(userKey);
        return ResponseEntity.ok(userKey);
    }
}
package com.github.alexcastro1919.simpleloginbackend.persistence.service.user;


import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.User;
import com.github.alexcastro1919.simpleloginbackend.persistence.repository.UserRepository;
import com.github.alexcastro1919.simpleloginbackend.web.security.user.UserAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para hacer de intermediario a los usuarios.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserAuthUtil userAuthUtil;

    public User create(User user) {
        return repository.insert(user);
    }

    public User getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User update(User user) {
        return repository.save(user);
    }

    public UserAuthResult auth(String email, String rawPassword) {
        User user = getByEmail(email);
        if (user == null) {
            return UserAuthResult.UNKNOWN_USER;
        }

        if (userAuthUtil.passwordMatches(rawPassword, user.getEncodedPassword())) {
            return UserAuthResult.SUCCESS;
        }

        return UserAuthResult.WRONG_PASSWORD;
    }
}
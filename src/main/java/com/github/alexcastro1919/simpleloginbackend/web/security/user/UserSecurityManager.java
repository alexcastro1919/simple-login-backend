package com.github.alexcastro1919.simpleloginbackend.web.security.user;

import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.key.UserKey;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserSecurityManager {

    private HashMap<String, UserKey> users;

    public UserSecurityManager() {
        users = new HashMap<>();
    }

    public void insertUser(UserKey key) {
        users.put(key.getBaseId(), key);
    }

    public void deleteUser(String id) {
        users.remove(id);
    }

    public boolean isValid(UserKey key) {
        return users.get(key.getBaseId()) != null && users.get(key.getBaseId()).equals(key);
    }
}

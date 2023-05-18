package com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.key;


import com.github.alexcastro1919.simpleloginbackend.persistence.entity.auth.Key;

/**
 * Clase hija de Key. Añade más constructores.
 */
public class UserKey extends Key {

    public UserKey(String baseId) {
        super(baseId);
    }

    public UserKey(String key, String baseId) {
        super(key, baseId);
    }
}

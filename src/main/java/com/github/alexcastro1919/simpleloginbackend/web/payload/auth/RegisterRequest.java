package com.github.alexcastro1919.simpleloginbackend.web.payload.auth;

public record RegisterRequest (String email, String password, String name, String lastName) {
}

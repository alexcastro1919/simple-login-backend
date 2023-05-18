package com.github.alexcastro1919.simpleloginbackend.web.payload.auth;

/**
 * Record para las solicitudes de login.
 */
public record LoginRequest (String email, String password){
}

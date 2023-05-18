package com.github.alexcastro1919.simpleloginbackend.persistence.entity.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Objeto User. Sirve para poder guardarlo en la base de datos y trabajar con usuarios fácilmente.
 */
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String email;
    private String encodedPassword;
    private String name;
    private String lastName;

    public User(String email, String encodedPassword, String name, String lastName) {
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

package com.github.alexcastro1919.simpleloginbackend.persistence.repository;

import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}

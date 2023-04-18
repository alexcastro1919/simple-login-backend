package com.github.alexcastro1919.simpleloginbackend.persistence.mapper;

import com.github.alexcastro1919.simpleloginbackend.domain.dto.UserDTO;
import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-18T19:51:46+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String id = null;
        String email = null;
        String name = null;
        String lastName = null;

        id = user.getId();
        email = user.getEmail();
        name = user.getName();
        lastName = user.getLastName();

        UserDTO userDTO = new UserDTO( id, email, name, lastName );

        return userDTO;
    }
}

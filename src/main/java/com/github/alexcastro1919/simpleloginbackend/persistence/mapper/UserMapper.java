package com.github.alexcastro1919.simpleloginbackend.persistence.mapper;

import com.github.alexcastro1919.simpleloginbackend.domain.dto.UserDTO;
import com.github.alexcastro1919.simpleloginbackend.persistence.entity.user.User;
import org.mapstruct.Mapper;

/**
 * Interficie para mapear los objetos User a UserDTO.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);
}

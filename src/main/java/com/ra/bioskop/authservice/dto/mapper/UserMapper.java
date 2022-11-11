package com.ra.bioskop.authservice.dto.mapper;

import org.springframework.stereotype.Component;

import com.ra.bioskop.authservice.dto.request.user.UserDTO;
import com.ra.bioskop.authservice.model.Users;

@Component
public class UserMapper {
    UserMapper() {}

    public static UserDTO toDto(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}

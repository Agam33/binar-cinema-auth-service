package com.ra.bioskop.authservice.service;

import com.ra.bioskop.authservice.dto.request.user.UserDTO;

public interface UserService {
    UserDTO findByEmail(String email);

    UserDTO register(UserDTO userDTO);

    UserDTO updateProfile(UserDTO userDTO);

    UserDTO deleteByEmail(String email);
}

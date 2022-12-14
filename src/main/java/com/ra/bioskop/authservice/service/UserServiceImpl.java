package com.ra.bioskop.authservice.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ra.bioskop.authservice.dto.mapper.UserMapper;
import com.ra.bioskop.authservice.dto.request.user.UserDTO;
import com.ra.bioskop.authservice.exception.BioskopException;
import com.ra.bioskop.authservice.exception.ExceptionType;
import com.ra.bioskop.authservice.model.Roles;
import com.ra.bioskop.authservice.model.Users;
import com.ra.bioskop.authservice.repository.RolesRepository;
import com.ra.bioskop.authservice.repository.UserRepository;
import com.ra.bioskop.authservice.util.Constants;

@Service
public class UserServiceImpl implements UserService {
     private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, RolesRepository rolesRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return UserMapper.toDto(user.get());
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                Constants.NOT_FOUND_MSG);
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<Users> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isEmpty()) {
            Optional<Roles> role = rolesRepository.findByName(userDTO.getRole());
            if(role.isPresent()) {
                Users userModel = new Users();
                userModel.setId(userDTO.getId());
                userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                userModel.setUsername(userDTO.getUsername());
                userModel.setEmail(userDTO.getEmail());
                userModel.setCreatedAt(LocalDateTime.now());
                userModel.setUpdatedAt(LocalDateTime.now());
                userModel.getRoles().add(role.get());
                userRepository.save(userModel);
                return userDTO;
            }
        }
        throw BioskopException.throwException(ExceptionType.DUPLICATE_ENTITY, HttpStatus.CONFLICT,
               Constants.ALREADY_EXIST_MSG);
    }

    @Override
    public UserDTO updateProfile(UserDTO userDTO) {
        Optional<Users> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isPresent()) {
            Users userModel = user.get();
            userModel.setUsername(userDTO.getUsername());
            userModel.setUpdatedAt(LocalDateTime.now());
            return UserMapper.toDto(userRepository.save(userModel));
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                Constants.NOT_FOUND_MSG);
    }

    @Override
    public UserDTO deleteByEmail(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Users userModel = user.get();
            userRepository.delete(userModel);
            return UserMapper.toDto(userModel);
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND,
                Constants.NOT_FOUND_MSG);
    }
}
package com.sammydev.usermanagementservice.service;

import com.sammydev.usermanagementservice.UserDTO.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    ResponseEntity<UserDTO> registerUser(UserDTO userDTO);

    ResponseEntity<UserDTO> getUserProfile(Long id);

    ResponseEntity<UserDTO> updateUser(UserDTO userDTO, Long id);

    ResponseEntity<UserDTO> deleteProfile(Long id);

    ResponseEntity<List<UserDTO>> getAllUsers();
}

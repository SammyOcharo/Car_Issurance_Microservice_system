package com.sammydev.usermanagementservice.service;

import com.sammydev.usermanagementservice.UserDTO.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDTO> registerUser(UserDTO userDTO);

    ResponseEntity<UserDTO> getUserProfile(Long id);

    ResponseEntity<UserDTO> updateUser(UserDTO userDTO, Long id);

    ResponseEntity<UserDTO> deleteProfile(Long id);
}

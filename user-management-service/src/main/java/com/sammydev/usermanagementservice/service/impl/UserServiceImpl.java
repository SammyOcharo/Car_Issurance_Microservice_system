package com.sammydev.usermanagementservice.service.impl;

import com.sammydev.usermanagementservice.UserDTO.UserDTO;
import com.sammydev.usermanagementservice.customException.EmailIsRequiredException;
import com.sammydev.usermanagementservice.customException.UserAlreadyExistsException;
import com.sammydev.usermanagementservice.customException.UserDoesNotExistException;
import com.sammydev.usermanagementservice.entity.UserEntity;
import com.sammydev.usermanagementservice.repository.UserRepository;
import com.sammydev.usermanagementservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<UserDTO> registerUser(UserDTO userDTO) {

        if(userDTO.getEmail() == null){
            throw new EmailIsRequiredException("Email is required");
        }
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new UserAlreadyExistsException("User already exists");
        }

        UserEntity userEntity = UserEntity.builder()
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phone(userDTO.getPhone())
                .build();

        userRepository.save(userEntity);

        UserDTO responseObject = UserDTO.builder()
                        .ResponseCode(200)
                                .ResponseMessage("User registered successfully")
                .build();

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> getUserProfile(Long id) {
        if(id == null){
            throw new UserDoesNotExistException("User does not exist!");
        }

        if(!userRepository.existsById(id)){
            throw new UserDoesNotExistException("User does not exist!");
        }
        UserEntity userEntity = userRepository.findById(id).get();

        UserDTO userDTO = UserDTO.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .build();

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}

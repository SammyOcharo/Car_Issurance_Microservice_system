package com.sammydev.usermanagementservice.service.impl;

import com.sammydev.usermanagementservice.UserDTO.UserDTO;
import com.sammydev.usermanagementservice.customException.EmailIsRequiredException;
import com.sammydev.usermanagementservice.customException.UserAlreadyExistsException;
import com.sammydev.usermanagementservice.customException.UserDoesNotExistException;
import com.sammydev.usermanagementservice.entity.UserEntity;
import com.sammydev.usermanagementservice.events.UserRegistrationEvent;
import com.sammydev.usermanagementservice.repository.UserRepository;
import com.sammydev.usermanagementservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserRegistrationEvent> kafkaTemplate;

    public UserServiceImpl(UserRepository userRepository, KafkaTemplate<String, UserRegistrationEvent> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
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

        //todo send async event to kafka to contact notification service.
        // Create and send user registration event
        UserRegistrationEvent event = new UserRegistrationEvent();
        event.setEmail(userEntity.getEmail());
        event.setMessage("Welcome to our car insurance service!");

        kafkaTemplate.send("user-registration", event);

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

    @Override
    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO, Long id) {
        if(id == null){
            throw new UserDoesNotExistException("User does not exist!");
        }

        if(!userRepository.existsById(id)){
            throw new UserDoesNotExistException("User does not exist!");
        }

        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPhone(userDTO.getPhone());

        userDTO.setResponseCode(200);
        userDTO.setResponseMessage("User updated successfully");

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> deleteProfile(Long id) {
        if(id == null){
            throw new UserDoesNotExistException("User does not exist!");
        }

        if(!userRepository.existsById(id)){
            throw new UserDoesNotExistException("User does not exist!");
        }

        UserEntity userEntity = userRepository.findById(id).get();
        userRepository.delete(userEntity);

        UserDTO userDTO = UserDTO.builder()
                .ResponseCode(200)
                .ResponseMessage("User deleted")
                .build();

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTO = userRepository.findAll()
                .stream()
                .map(this::mapEntityToDto)
                .toList();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    private UserDTO mapEntityToDto(UserEntity userEntity) {
        return UserDTO.builder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phone(userEntity.getPhone())
                .build();
    }
}

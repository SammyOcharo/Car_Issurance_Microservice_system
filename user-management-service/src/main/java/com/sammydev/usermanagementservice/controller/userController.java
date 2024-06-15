package com.sammydev.usermanagementservice.controller;

import com.sammydev.usermanagementservice.UserDTO.UserDTO;
import com.sammydev.usermanagementservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apps/api/v1/user/")
public class userController {

    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    //register user
    @PostMapping("register-user/")
    public ResponseEntity<UserDTO> registerUser(@RequestBody  UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    //Get user profile
    @GetMapping("user-{id}-profile/")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long id) {
        return userService.getUserProfile(id);
    }
    //Update user information

    //Delete user

    //Get all users


}

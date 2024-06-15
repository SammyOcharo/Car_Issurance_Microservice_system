package com.sammydev.usermanagementservice.globalException;

import com.sammydev.usermanagementservice.UserDTO.UserDTO;
import com.sammydev.usermanagementservice.UserDTO.UserException;
import com.sammydev.usermanagementservice.customException.EmailIsRequiredException;
import com.sammydev.usermanagementservice.customException.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.sammydev.usermanagementservice")
public class GlobalExceptionHandler {

    UserDTO userDTO = new UserDTO();
    UserException userException = new UserException();

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<UserDTO> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        userDTO.setResponseMessage(ex.getMessage());
        userDTO.setResponseCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailIsRequiredException.class)
    public ResponseEntity<UserException> handleEmailIsRequiredException(EmailIsRequiredException ex) {
        userException.setResponseMessage(ex.getMessage());
        userException.setResponseCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(userException, HttpStatus.BAD_REQUEST);
    }




}

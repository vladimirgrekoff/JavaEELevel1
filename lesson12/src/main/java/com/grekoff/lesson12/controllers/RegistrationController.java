package com.grekoff.lesson12.controllers;

import com.grekoff.lesson12.dto.RegistrationResponse;
import com.grekoff.lesson12.dto.UserDto;
import com.grekoff.lesson12.dto.UserRegistrationDto;
import com.grekoff.lesson12.entities.User;
import com.grekoff.lesson12.exceptions.AppError;
import com.grekoff.lesson12.exceptions.EmailExistsException;
import com.grekoff.lesson12.exceptions.UserAlreadyExistException;
import com.grekoff.lesson12.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final UsersService usersService;



    @PostMapping
    public ResponseEntity<?> registerUserAccount(@RequestBody UserRegistrationDto userRegistrationDto) throws UserAlreadyExistException, EmailExistsException {
        System.out.println("name=" + userRegistrationDto.getUsername() + ", " + "email=" + userRegistrationDto.getEmail() + ", " + "password=" + userRegistrationDto.getPassword() + ", " + "confirmPassword=" + userRegistrationDto.getConfirmPassword());
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }

        User registered = usersService.registerNewUserAccount(userRegistrationDto);
        System.out.println("ответ " + registered);
        if (registered==null){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        userRegistrationDto.setUsername(null);
        userRegistrationDto.setEmail(null);
        userRegistrationDto.setPassword(null);
        userRegistrationDto.setConfirmPassword(null);
        return  ResponseEntity.ok(userRegistrationDto);
    }
}

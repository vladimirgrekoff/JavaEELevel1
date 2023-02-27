package com.grekoff.lesson12.controllers;

import com.grekoff.lesson12.converters.UserConverter;
import com.grekoff.lesson12.dto.UserDto;
import com.grekoff.lesson12.entities.Role;
import com.grekoff.lesson12.entities.User;
import com.grekoff.lesson12.exceptions.ResourceNotFoundException;
import com.grekoff.lesson12.services.UsersService;
import com.grekoff.lesson12.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    @Autowired
    private UserConverter userConverter;

    private final UserValidator userValidator;


    // GET http://localhost:8189/lesson12

    @GetMapping
    public List<UserDto> getAllUsers() {
        return usersService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = usersService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Пользователь отсутствует в списке, id: " + id));
        UserDto userDto = userConverter.entityToDto(user);
        userDto.setPassword("PROTECTED");
        return userDto;
    }

    @PostMapping
    public UserDto addNewUser(@RequestBody UserDto userDto) {
        userValidator.validate(userDto);
        User user = userConverter.dtoToEntity(userDto);
        user = usersService.save(user);
        return userConverter.entityToDto(user);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        userValidator.validate(userDto);
        User user = usersService.update(userDto);
        return userConverter.entityToDto(user);
    }


    @DeleteMapping ("/{id}")
    public void deleteById(@PathVariable Long id){
        usersService.deleteById(id);
    }

}

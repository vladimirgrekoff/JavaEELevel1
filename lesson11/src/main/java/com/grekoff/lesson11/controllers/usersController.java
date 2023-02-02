package com.grekoff.lesson11.controllers;

import com.grekoff.lesson11.converters.UserConverter;
import com.grekoff.lesson11.dto.UserDto;
import com.grekoff.lesson11.entities.User;
import com.grekoff.lesson11.exceptions.ResourceNotFoundException;
import com.grekoff.lesson11.services.UsersService;
import com.grekoff.lesson11.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class usersController {

    private final UsersService usersService;

    private UserConverter userConverter;

    private final UserValidator userValidator;


    // GET http://localhost:8189/lesson11

    @GetMapping
    public List<User> getAllUsers() {
         return usersService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = usersService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Пользователь отсутствует в списке, id: " + id));
        return userConverter.entityToDto(user);
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

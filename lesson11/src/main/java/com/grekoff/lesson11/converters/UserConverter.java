package com.grekoff.lesson11.converters;


import com.grekoff.lesson11.dto.UserDto;
import com.grekoff.lesson11.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User dtoToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), userDto.getRoles());
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getPassword(), user.getRoles());
    }

}

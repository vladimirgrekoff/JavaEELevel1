package com.grekoff.lesson12.converters;


import com.grekoff.lesson12.dto.UserDto;
import com.grekoff.lesson12.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserConverter {

    public User dtoToEntity(UserDto userDto) {
        log.info(String.valueOf(userDto));///////////////////////////////
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), userDto.getRoles());
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRoles());
    }
}

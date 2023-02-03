package com.grekoff.lesson11.dto;

import com.grekoff.lesson11.entities.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    public Long id;
    private String username;
    private String password;
    private String email;
//    private Collection<Role> roles;
}

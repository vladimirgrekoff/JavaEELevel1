package com.grekoff.lesson12.dto;

import com.grekoff.lesson12.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    public Long id;
    private String username;
    private String password;
    private String email;

    private Collection<Role> roles;
}

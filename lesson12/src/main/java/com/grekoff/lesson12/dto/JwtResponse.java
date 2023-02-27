package com.grekoff.lesson12.dto;

import com.grekoff.lesson12.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class JwtResponse {
//    private Collection<Role> roles;
    private String token;
}

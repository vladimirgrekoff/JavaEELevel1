package com.grekoff.lesson12.dto;

import org.springframework.http.HttpStatus;

public class RegistrationResponse {

    private HttpStatus status;

    private UserRegistrationDto userRegistrationDto;

    public RegistrationResponse(HttpStatus status, UserRegistrationDto userRegistrationDto) {
        this.status = status;
        this.userRegistrationDto = userRegistrationDto;
    }

    public RegistrationResponse(UserRegistrationDto userRegistrationDto) {
        this.userRegistrationDto = userRegistrationDto;
    }

    public RegistrationResponse(HttpStatus status) {

    }
}

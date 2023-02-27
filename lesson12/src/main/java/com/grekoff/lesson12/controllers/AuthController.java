package com.grekoff.lesson12.controllers;


import com.grekoff.lesson12.dto.JwtRequest;
import com.grekoff.lesson12.dto.JwtResponse;
import com.grekoff.lesson12.entities.User;
import com.grekoff.lesson12.exceptions.AppError;
import com.grekoff.lesson12.services.UsersService;
import com.grekoff.lesson12.utils.JwtTokenUtil;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Base64;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UsersService usersService;

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Имя пользователя или пароль не найдены"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = usersService.loadUserByUsername(authRequest.getUsername());
//        Collection roles = userDetails.getAuthorities();//////////////////////////////////
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    @RequestMapping("/user")
//    public Principal user(Principal user) {
//    public Collection<? extends org.springframework.security.core.GrantedAuthority> user(HttpServletRequest request) {
//        System.out.println("вызов user");
//        Authentication a = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Authentication " + a);
//        return a.getAuthorities();
        public Principal user(HttpServletRequest request) {
            System.out.println("вызов user");
            String authToken = request.getHeader("Authorization").substring("Basic ".length()).trim();
            System.out.println("authToken " + authToken);
            return () ->  new String(Base64.getDecoder().decode(authToken)).split(":")[0];
        }
}

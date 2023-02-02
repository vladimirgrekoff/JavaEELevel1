package com.grekoff.lesson11.controllers;


import com.grekoff.lesson11.entities.User;
import com.grekoff.lesson11.services.UsersService;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SecurityController {
    private final UsersService usersService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/verification")
    @ResponseStatus(HttpStatus.OK)
//    public AuthResponse verification(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
    public Principal verification(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
        System.out.println("вызов login");/////////////////////////////////////////////
        System.out.println(username);/////////////////////////////////////////////
//        Authentication authentication;
        Principal principal;
        try {
//            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
            principal = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println(principal);/////////////////////////////////////////////
        } catch (BadCredentialsException e) {
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Имя или пароль неправильны", e);
        }
//        return new AuthResponse(authentication.toString());
        return principal;
    }

//    @PostMapping("/validation")
//    public AuthResponse loginUser(@RequestBody HttpServletRequest request){
//        DaoAuthenticationProvider daoAuthenticationProvider;
//        Authentication authentication = daoAuthenticationProvider.authenticate(u);
//    }


    @RequestMapping("/user")
//    public Principal user(Principal user) {
//    public Collection<? extends org.springframework.security.core.GrantedAuthority> user(HttpServletRequest request) {
//        System.out.println("вызов user");
//        Authentication a = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Authentication " + a);
//        return a.getAuthorities();
        public Principal user(HttpServletRequest request) {
            System.out.println("вызов user");
            String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
            System.out.println("authToken " + authToken);
            return () ->  new String(Base64.getDecoder().decode(authToken)).split(":")[0];
        }
//        UserDetails userDetails = userService.loadUserByUsername(user.getName());
//        return "Authenticated user info: " + userDetails.getUsername() + " authorities: " + userDetails.getAuthorities();
//        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
//        System.out.println("authToken " + authToken);
//
//        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
//    }

    @GetMapping("/home")
    @PermitAll
    public String home(Principal principal){
        return "index";
    }

    @GetMapping("/unsecured")
    @PermitAll
    public String unsecuredPage() {
//        return "/unsecured";
        return "unsecured";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal) {
//        Authentication a = SecurityContextHolder.getContext().getAuthentication();
//        Optional<User> user = userService.findByUsername(principal.getName());
//        return "secured part of web services" + principal.getUsername() + " " + user.get().getEmail();
//        return "secured part of web services " + principal.getName();
        User user = usersService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Зарегистрированый пользователь : " + user.getUsername() + " : " + user.getEmail();


//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        return "Authenticated user info: " + userDetails.getUsername() + " authorities: " + userDetails.getAuthorities();
    }

    @GetMapping("/hello")
    public String hello(Principal principal){
        return "Hello user " + principal.getName();
    }

    @GetMapping("/profile")
    public String pageForReadProfile(Principal principal) {
        return "read profile page " + principal.getName();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
//    @PostAuthorize("hasRole('ADMIN')")
    public String admin(Principal principal){
        return "admins page";
    }
}

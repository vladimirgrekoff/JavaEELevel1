package com.grekoff.lesson12.configs;

import com.grekoff.lesson12.services.UsersService;
import io.jsonwebtoken.impl.Base64Codec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Base64;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
//    private final UsersService usersService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/users/**").hasRole("ADMIN")
                .requestMatchers("/products/**").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers("/api/v1/cart/**").hasAnyRole("USER", "MANAGER", "ADMIN")
                .requestMatchers("/api/v1/profile/**", "/welcome", "/navigation").permitAll()
                .requestMatchers("/**", "/resources/**", "/resources/templates/**", "/api/v1/auth/**").permitAll()
//                .requestMatchers("/api/v1/admin/**","/api/v1/profile/**","/api/v1/authenticated/**").authenticated()
//                .anyRequest().denyAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
//                .exceptionHandling().accessDeniedPage("/accessDenied")
//                .and()
//                .httpBasic()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/auth/token")
//                .successForwardUrl("/navigation")
                .and()
                .logout()
//                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console");
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }






    @Bean
    public AuthenticationManager  authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject (AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }


//    @Bean
//    public AuthenticationManager  authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject (AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(usersService).passwordEncoder(passwordEncoder());
//        return authenticationManagerBuilder.build();
//    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(usersService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UsersService usersService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(usersService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }

//    @Bean
//    public Base64Codec base64Codec() {
//        return new Base64Codec();
//    }

//    @Bean
//    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil) {
//        return new JwtRequestFilter(jwtTokenUtil);
//    }
}

package com.grekoff.lesson11.configs;

//import org.springframework.security.core.userdetails.User;


//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationProvider implements AuthenticationProvider {

//    private final UserRepository userRepository;
//    private UserService userService;
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println(authentication);/////////////////////////////////////////////
//        String userName = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        User realUser = userService.findByUsername(userName).get();
//
////        User realUser = userRepository.findByUsername(userName).get();
//
//        if (realUser == null) {
//            throw new BadCredentialsException("Неизвестный пользователь " + userName);
//        }
//
//        if (!password.equals(passwordEncoder.encode(realUser.getPassword())) ) {
//            throw new BadCredentialsException("Неправильный пароль");
//        }
//
//
//        UserDetails principal = org.springframework.security.core.userdetails.User.builder()
//                .username(realUser.getUsername())
//                .password(realUser.getPassword())
//                .roles(String.valueOf(realUser.getRoles()))
//                .build();
//
//        return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//    @Autowired
//    public void setUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//    @Autowired
//    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

//}

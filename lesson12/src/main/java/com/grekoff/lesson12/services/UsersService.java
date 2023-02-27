package com.grekoff.lesson12.services;

import com.grekoff.lesson12.converters.UserConverter;
import com.grekoff.lesson12.dto.UserDto;
import com.grekoff.lesson12.dto.UserRegistrationDto;
import com.grekoff.lesson12.entities.Role;
import com.grekoff.lesson12.entities.User;
import com.grekoff.lesson12.exceptions.EmailExistsException;
import com.grekoff.lesson12.exceptions.ResourceNotFoundException;
import com.grekoff.lesson12.exceptions.UserAlreadyExistException;
import com.grekoff.lesson12.repositories.RoleRepository;
import com.grekoff.lesson12.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = usersRepository.findAll();
        for (User u: userList) {
            UserDto userDto = userConverter.entityToDto(u);
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Transactional
    public User registerNewUserAccount(UserRegistrationDto userRegistrationDto) throws EmailExistsException, UserAlreadyExistException {
        if (emailExists(userRegistrationDto.getEmail())) {
            throw new UserAlreadyExistException(
                    "Существует учетная запись с этим адресом электронной почты:" + userRegistrationDto.getEmail());
        }
        User user = new User();
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
        user.setUsername(userRegistrationDto.getUsername());

        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        user.setEmail(userRegistrationDto.getEmail());
        Role role = new Role();
        role.setId(roleRepository.findByName("ROLE_USER").get().getId());
        role.setName("ROLE_USER");
        user.setRoles(List.of(role));
        return usersRepository.save(user);
    }

    private boolean emailExists(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }
    @Transactional
    public User save(User user) {
        List<Role> roles = user.getRoles().stream().toList();
        for(Role role : roles){
            if(role.getId() == null) {
                role.setId(roleRepository.findByName(role.getName()).get().getId());
            }
        }
        user.setRoles(roles);
        System.out.println("роли нового: " + user.getRoles());///////////////////////
        return usersRepository.save(user);
    }
    @Transactional
    public User update(UserDto userDto) {
        User user = usersRepository.findById(userDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Пользователь отсутствует в списке, id: " + userDto.getId()));
        user.setUsername(userDto.getUsername());
        if(!(userDto.getPassword().equals("PROTECTED"))) {
            user.setPassword(userDto.getPassword());
        }
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles());
        return usersRepository.save(user);
    }

    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }
}

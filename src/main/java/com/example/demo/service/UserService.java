package com.example.demo.service;


import com.example.demo.dto.UserDto;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
//@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    public Optional<User> getUserByEmail(String email) {
//        log.info("Gol user by email:" + email);
        Optional<User> mayBeUser = userRepository.findUserByEmail(email);
        return mayBeUser;
    }


    public Optional<User> getUserById(int id) {
//        log.info("Got user by id:" + id);
        return userRepository.findById(id);
    }


    public int save(UserDto userDto) {
//        log.info("The user:" + userDto.getEmail() + " is saved!");
        return userRepository.save(User.builder()
                .fio(userDto.getFio())
                .email(userDto.getEmail())
                .password(encoder.encode(userDto.getPassword()))
                .phoneNumber(userDto.getPhoneNumber())
                .enabled(true)
                .role(roleService.getRoleById(1))
                .build()).getId();

    }


    public UserDto mapToUserDto(User user) {
        if (user != null) {
            return UserDto.builder()
                    .id(user.getId())
                    .fio(user.getFio())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .phoneNumber(user.getPhoneNumber())
                    .build();
        } else {
            return null;
        }
    }


}

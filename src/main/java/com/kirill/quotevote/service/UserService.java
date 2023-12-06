package com.kirill.quotevote.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import com.kirill.quotevote.dto.UserDto;
import com.kirill.quotevote.entity.User;
import com.kirill.quotevote.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto create(String name, String email, String password) {
        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .creationDate(LocalDate.now())
                .build();
        return this.toDto(userRepository.save(user));
    }

    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public UserDto toDto(User user) {
        return UserDto.builder().name(user.getName()).id(user.getId()).build();
    }
}

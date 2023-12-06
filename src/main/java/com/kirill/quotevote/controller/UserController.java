package com.kirill.quotevote.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.kirill.quotevote.dto.UserDto;
import com.kirill.quotevote.dto.request.CreateUserRequestDto;
import com.kirill.quotevote.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public UserDto createUser(@RequestBody CreateUserRequestDto request) {
        return userService.create(request.getName(), request.getEmail(), request.getPassword());
    }


}

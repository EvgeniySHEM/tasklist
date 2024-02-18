package ru.sanctio.tasklist.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sanctio.tasklist.domain.user.User;
import ru.sanctio.tasklist.service.AuthService;
import ru.sanctio.tasklist.service.UserService;
import ru.sanctio.tasklist.web.dto.auth.JwtRequest;
import ru.sanctio.tasklist.web.dto.auth.JwtResponse;
import ru.sanctio.tasklist.web.dto.user.UserDto;
import ru.sanctio.tasklist.web.dto.validation.OnCreate;
import ru.sanctio.tasklist.web.mappers.UserMapper;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody final JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}

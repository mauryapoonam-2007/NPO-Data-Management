package com.npo.management.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.npo.management.dto.LoginRequest;
import com.npo.management.model.User;
import com.npo.management.service.UserService;
import com.npo.management.dto.LoginResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return userService.loginUser(
            request.getEmail(),
            request.getPassword()
        );
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication) {
        return "Authenticated user: " + authentication.getName();
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Welcome Admin";
    }

    @GetMapping("/helper")
    public String helperAccess() {
        return "Welcome Helper";
    }
}
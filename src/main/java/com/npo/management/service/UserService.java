package com.npo.management.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.npo.management.dto.LoginResponse;
import com.npo.management.model.User;
import com.npo.management.repository.UserRepository;
import com.npo.management.security.JwtService;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository , JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public User registerUser(User user) {
        if (user.getEmail() == null ||
        !user.getEmail().toLowerCase().endsWith("@gmail.com")) {

        throw new RuntimeException(
            "Only Gmail addresses are allowed"
        );
    }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("HELPER");
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
    public LoginResponse loginUser(String email, String password) {

        User user = userRepository.findTopByEmailOrderByCreatedAtDesc(email);

        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        String token = jwtService.generateToken(
        user.getEmail(),
        user.getRole()
        );

        return new LoginResponse(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            user.getCity(),
            user.getRole(),
            user.getCreatedAt(),
            token
        );
    }
}
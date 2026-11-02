
package com.npo.management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.npo.management.dto.UserListResponse;
import com.npo.management.model.User;
import com.npo.management.repository.UserRepository;

@Service
public class AdminService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createAdmin(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        user.setRole("ADMIN");
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User createBeneficiary(User user) {

        if (user.getEmail() == null ||
                !user.getEmail().toLowerCase().endsWith("@gmail.com")) {
            throw new RuntimeException("Only Gmail addresses are allowed");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        user.setRole("BENEFICIARY");
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public List<UserListResponse> getAllUsers() {

        return userRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(user -> new UserListResponse(
                        user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getCity(),
                        user.getRole(),
                        user.getCreatedAt()))
                .toList();
    }

    public List<UserListResponse> getAllHelpers() {

        return userRepository.findByRoleOrderByCreatedAtDesc("HELPER")
                .stream()
                .map(user -> new UserListResponse(
                        user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getCity(),
                        user.getRole(),
                        user.getCreatedAt()))
                .toList();
    }
}

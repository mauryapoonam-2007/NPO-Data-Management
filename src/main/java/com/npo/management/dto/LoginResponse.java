package com.npo.management.dto;

import java.time.LocalDateTime;

public class LoginResponse {

    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String city;
    private String role;
    private LocalDateTime createdAt;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String id, String fullName, String email,
                         String phone, String city, String role,
                         LocalDateTime createdAt,String token) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.role = role;
        this.createdAt = createdAt;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getToken() {
        return token;
    }
}

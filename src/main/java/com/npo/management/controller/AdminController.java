package com.npo.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.npo.management.dto.UserListResponse;
import com.npo.management.model.User;
import com.npo.management.service.AdminService;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    

    @PostMapping("/admin/create")
    public User createAdmin(@RequestBody User user) {
        return adminService.createAdmin(user);
    }
    @PostMapping("/admin/create-beneficiary")
public User createBeneficiary(@RequestBody User user) {
    return adminService.createBeneficiary(user);
}
@GetMapping("/admin/users")
public List<UserListResponse> getAllUsers() {
    return adminService.getAllUsers();
}

@GetMapping("/admin/helpers")
public List<UserListResponse> getAllHelpers() {
    return adminService.getAllHelpers();
}
}
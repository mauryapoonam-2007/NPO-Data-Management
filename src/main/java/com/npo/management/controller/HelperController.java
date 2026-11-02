package com.npo.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelperController {

    @GetMapping("/helper/dashboard")
    public String helperDashboard() {

        return "Welcome Helper! This is Helper Dashboard.";
    }
}
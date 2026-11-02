package com.npo.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npo.management.dto.DashboardResponse;
import com.npo.management.service.DashboardService;

@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping("/admin/dashboard")
public DashboardResponse getDashboard() {
    return dashboardService.getDashboard();
}

    
}
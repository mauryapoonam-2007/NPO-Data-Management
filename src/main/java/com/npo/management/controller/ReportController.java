package com.npo.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npo.management.dto.ReportResponse;
import com.npo.management.service.ReportService;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/admin/reports")
    public ReportResponse getOverallReport() {
        return reportService.getOverallReport();
    }
}
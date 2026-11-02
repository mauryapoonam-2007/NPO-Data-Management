package com.npo.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.npo.management.model.Donation;
import com.npo.management.service.DonationService;

@RestController
public class DonationController {

    private final DonationService donationService;

    public DonationController(
            DonationService donationService) {

        this.donationService = donationService;
    }

    // Public donation
    @PostMapping("/donations")
    public Donation createDonation(
            @RequestBody Donation donation) {

        return donationService.createDonation(donation);
    }

    // Admin - all donations
    @GetMapping("/admin/donations")
    public List<Donation> getAllDonations() {

        return donationService.getAllDonations();
    }

    // Admin - pending donations
    @GetMapping("/admin/donations/pending")
    public List<Donation> getPendingDonations() {

        return donationService.getPendingDonations();
    }

    // Admin - donation by ID
    @GetMapping("/admin/donations/{id}")
    public Donation getDonationById(
            @PathVariable String id) {

        return donationService.getDonationById(id);
    }

    // Admin - verify donation
    @PutMapping("/admin/donations/{id}/verify")
    public Donation verifyDonation(
            @PathVariable String id) {

        return donationService.verifyDonation(id);
    }

    // Admin - reject donation
    @PutMapping("/admin/donations/{id}/reject")
    public Donation rejectDonation(
            @PathVariable String id) {

        return donationService.rejectDonation(id);
    }

    @GetMapping("/admin/donations/search")
    public List<Donation> searchDonations(
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String city) {

        if (status != null) {
            return donationService.searchByStatus(status);
        }

        if (type != null) {
            return donationService.searchByType(type);
        }

        if (category != null) {
            return donationService.searchByCategory(category);
        }

        if (city != null) {
            return donationService.searchByCity(city);
        }

            return donationService.getAllDonations();
    }
}
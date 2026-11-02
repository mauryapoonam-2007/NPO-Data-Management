package com.npo.management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.npo.management.model.Donation;
import com.npo.management.repository.DonationRepository;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Donation createDonation(Donation donation) {

        if (donation.getType() == null ||
            (!donation.getType().equals("MONEY") &&
             !donation.getType().equals("MATERIAL"))) {

            throw new RuntimeException(
                "Donation type must be MONEY or MATERIAL");
        }

        if (donation.getType().equals("MONEY")) {

            if (donation.getAmount() <= 0) {
                throw new RuntimeException(
                    "Money donation amount must be greater than 0");
            }

        } else {

            if (donation.getQuantity() <= 0) {
                throw new RuntimeException(
                    "Material donation quantity must be greater than 0");
            }

            if (donation.getItemName() == null ||
                donation.getItemName().isBlank()) {

                throw new RuntimeException(
                    "Item name is required for material donation");
            }
        }

        donation.setStatus("PENDING");
        donation.setDonatedAt(LocalDateTime.now());
        donation.setVerifiedAt(null);

        return donationRepository.save(donation);
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public List<Donation> getPendingDonations() {
        return donationRepository.findByStatus("PENDING");
    }

    public Donation getDonationById(String id) {

        return donationRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Donation not found"));
    }

    public Donation verifyDonation(String id) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Donation not found"));

        if (!"PENDING".equals(donation.getStatus())) {
            throw new RuntimeException(
                "Only PENDING donations can be verified");
        }

        donation.setStatus("RECEIVED");
        donation.setVerifiedAt(LocalDateTime.now());

        return donationRepository.save(donation);
    }

    public Donation rejectDonation(String id) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Donation not found"));

        if (!"PENDING".equals(donation.getStatus())) {
            throw new RuntimeException(
                "Only PENDING donations can be rejected");
        }

        donation.setStatus("REJECTED");

        return donationRepository.save(donation);
    }
    public List<Donation> searchByStatus(String status) {
        return donationRepository.findByStatus(status);
    }

    public List<Donation> searchByType(String type) {
        return donationRepository.findByType(type);
    }

    public List<Donation> searchByCategory(String category) {
        return donationRepository.findByCategory(category);
    }

    public List<Donation> searchByCity(String city) {
        return donationRepository.findByCity(city);
    }
}

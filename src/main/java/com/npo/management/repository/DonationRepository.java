package com.npo.management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.npo.management.model.Donation;

public interface DonationRepository
        extends MongoRepository<Donation, String> {

    List<Donation> findByStatus(String status);

    List<Donation> findByType(String type);

    List<Donation> findByCategory(String category);

    List<Donation> findByCity(String city);

    List<Donation> findByDonorEmail(String donorEmail);
}
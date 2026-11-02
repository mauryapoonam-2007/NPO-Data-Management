package com.npo.management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.npo.management.model.HelpRequest;

public interface HelpRequestRepository
        extends MongoRepository<HelpRequest, String> {

    List<HelpRequest> findByStatus(String status);

    List<HelpRequest> findByCity(String city);

    List<HelpRequest> findByPriority(String priority);

    List<HelpRequest> findByBeneficiaryId(String beneficiaryId);

    List<HelpRequest> findByAssignedHelperId(String assignedHelperId);

    List<HelpRequest> findByCategory(String category);

    
}
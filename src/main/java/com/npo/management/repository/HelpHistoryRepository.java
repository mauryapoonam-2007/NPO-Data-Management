package com.npo.management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.npo.management.model.HelpHistory;

public interface HelpHistoryRepository
        extends MongoRepository<HelpHistory, String> {

    List<HelpHistory> findByHelperId(String helperId);

    List<HelpHistory> findByBeneficiaryId(String beneficiaryId);

    List<HelpHistory> findByCategory(String category);

    List<HelpHistory> findByCity(String city);
}
package com.npo.management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.npo.management.model.HelperImpact;

public interface HelperImpactRepository
        extends MongoRepository<HelperImpact, String> {

    HelperImpact findByHelperId(String helperId);

    List<HelperImpact> findAllByOrderByPointsDesc();

    


}
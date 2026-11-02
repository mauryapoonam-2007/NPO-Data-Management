package com.npo.management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.npo.management.model.Education;

public interface EducationRepository
        extends MongoRepository<Education, String> {

    List<Education> findByChildId(String childId);

    List<Education> findByChildNameContainingIgnoreCase(
            String childName);

    List<Education> findBySchoolNameContainingIgnoreCase(
            String schoolName);

    List<Education> findByStatus(String status);
}
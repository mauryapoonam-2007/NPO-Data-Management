package com.npo.management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.npo.management.model.Child;

public interface ChildRepository extends MongoRepository<Child, String> {

    List<Child> findByStatus(String status);

    List<Child> findByGender(String gender);

    List<Child> findByCityIgnoreCase(String city);

    List<Child> findByChildNameContainingIgnoreCase(String childName);
}
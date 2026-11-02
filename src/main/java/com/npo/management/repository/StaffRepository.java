package com.npo.management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.npo.management.model.Staff;

public interface StaffRepository extends MongoRepository<Staff, String> {

    List<Staff> findByStatus(String status);

    List<Staff> findByDepartmentIgnoreCase(String department);

    List<Staff> findByFullNameContainingIgnoreCase(String fullName);

    List<Staff> findByCityIgnoreCase(String city);
}
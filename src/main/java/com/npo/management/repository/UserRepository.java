package com.npo.management.repository;

import com.npo.management.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);

    User findTopByEmailOrderByCreatedAtDesc(String email);

    boolean existsByRole(String role);
    
List<User> findAllByOrderByCreatedAtDesc();

List<User> findByRoleOrderByCreatedAtDesc(String role);
}
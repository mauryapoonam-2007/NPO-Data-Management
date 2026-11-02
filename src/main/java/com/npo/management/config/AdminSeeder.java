package com.npo.management.config;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.npo.management.model.User;
import com.npo.management.repository.UserRepository;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MongoDatabaseFactory mongoDatabaseFactory;
    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AdminSeeder(UserRepository userRepository,
                   MongoDatabaseFactory mongoDatabaseFactory) {
    this.userRepository = userRepository;
    this.mongoDatabaseFactory = mongoDatabaseFactory;
}

    @Override
    public void run(String... args) {
            System.out.println(
        "CONNECTED DATABASE = "
        + mongoDatabaseFactory.getMongoDatabase().getName()
    );

    System.out.println("ADMIN SEEDER IS RUNNING");

        if (!userRepository.existsByRole("ADMIN")) {
            System.out.println("ADMIN SEEDER IS RUNNING");

            User admin = new User();

            admin.setFullName("NPO Admin");
            admin.setEmail("admin@npo.com");
            admin.setPassword(
                passwordEncoder.encode("Admin@123")
            );
            admin.setPhone("9999999999");
            admin.setCity("Mumbai");
            admin.setRole("ADMIN");
            admin.setCreatedAt(LocalDateTime.now());

            userRepository.save(admin);

            System.out.println("Default ADMIN created successfully.");
        }else {
            System.out.println("ADMIN already exists.");
        }
    }
}
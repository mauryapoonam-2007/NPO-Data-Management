package com.npo.management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.npo.management.model.Inventory;

public interface InventoryRepository
        extends MongoRepository<Inventory, String> {

    List<Inventory> findByCategory(String category);

    List<Inventory> findByLocation(String location);

    List<Inventory> findByItemName(String itemName);
}
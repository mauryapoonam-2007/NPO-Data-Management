package com.npo.management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.npo.management.model.Inventory;
import com.npo.management.repository.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory addItem(Inventory inventory) {
        inventory.setCreatedAt(LocalDateTime.now());
        inventory.setUpdatedAt(LocalDateTime.now());

        if (inventory.getQuantity() < 0) {
            throw new RuntimeException("Quantity cannot be negative");
        }

        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }

    public Inventory getItemById(String id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Inventory item not found"));
    }

    public Inventory updateQuantity(String id, int quantity) {

    Inventory inventory = inventoryRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Inventory item not found"));

    if (quantity < 0) {
        throw new RuntimeException(
            "Quantity cannot be negative");
    }

    inventory.setQuantity(quantity);
    
    inventory.setUpdatedAt(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> searchByCategory(String category) {
        return inventoryRepository.findByCategory(category);
    }

    public List<Inventory> searchByLocation(String location) {
        return inventoryRepository.findByLocation(location);
    }

    public List<Inventory> searchByItemName(String itemName) {
        return inventoryRepository.findByItemName(itemName);
    }
    
}
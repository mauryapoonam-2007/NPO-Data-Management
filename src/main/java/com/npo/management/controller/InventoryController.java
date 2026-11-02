package com.npo.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.npo.management.model.Inventory;
import com.npo.management.service.InventoryService;

@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/admin/inventory")
    public Inventory addItem(@RequestBody Inventory inventory) {
        return inventoryService.addItem(inventory);
    }

    @GetMapping("/admin/inventory")
    public List<Inventory> getAllItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/admin/inventory/{id}")
    public Inventory getItemById(@PathVariable String id) {
        return inventoryService.getItemById(id);
    }

    @PutMapping("/admin/inventory/{id}/quantity")
    public Inventory updateQuantity(
        @PathVariable String id,
        @RequestParam int quantity) {

            return inventoryService.updateQuantity(id, quantity);
    }

    @GetMapping("/admin/inventory/search")
public List<Inventory> searchInventory(
        @RequestParam(required = false) String itemName,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String location) {

    if (itemName != null) {
        return inventoryService.searchByItemName(itemName);
    }

    if (category != null) {
        return inventoryService.searchByCategory(category);
    }

    if (location != null) {
        return inventoryService.searchByLocation(location);
    }

    return inventoryService.getAllItems();
}
}

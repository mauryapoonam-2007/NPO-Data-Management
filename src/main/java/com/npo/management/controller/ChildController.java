package com.npo.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.npo.management.model.Child;
import com.npo.management.service.ChildService;

@RestController
@RequestMapping("/admin/children")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping
    public ResponseEntity<Child> addChild(@RequestBody Child child) {
        return ResponseEntity.ok(childService.addChild(child));
    }

    @GetMapping
    public ResponseEntity<List<Child>> getAllChildren() {
        return ResponseEntity.ok(childService.getAllChildren());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Child> getChildById(@PathVariable String id) {
        return ResponseEntity.ok(childService.getChildById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Child> updateChild(
            @PathVariable String id,
            @RequestBody Child child) {

        return ResponseEntity.ok(
                childService.updateChild(id, child)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChild(@PathVariable String id) {

        childService.deleteChild(id);

        return ResponseEntity.ok("Child deleted successfully");
    }

    @GetMapping("/active")
    public ResponseEntity<List<Child>> getActiveChildren() {
        return ResponseEntity.ok(childService.getActiveChildren());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Child>> searchChildren(
            @RequestParam String name) {

        return ResponseEntity.ok(
                childService.searchChildren(name)
        );
    }
}
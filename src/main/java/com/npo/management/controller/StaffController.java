package com.npo.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.npo.management.model.Staff;
import com.npo.management.service.StaffService;

@RestController
@RequestMapping("/admin/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<Staff> addStaff(
            @RequestBody Staff staff) {

        return ResponseEntity.ok(
                staffService.addStaff(staff)
        );
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {

        return ResponseEntity.ok(
                staffService.getAllStaff()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(
            @PathVariable String id) {

        return ResponseEntity.ok(
                staffService.getStaffById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(
            @PathVariable String id,
            @RequestBody Staff staff) {

        return ResponseEntity.ok(
                staffService.updateStaff(id, staff)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(
            @PathVariable String id) {

        staffService.deleteStaff(id);

        return ResponseEntity.ok(
                "Staff deleted successfully"
        );
    }

    @GetMapping("/active")
    public ResponseEntity<List<Staff>> getActiveStaff() {

        return ResponseEntity.ok(
                staffService.getActiveStaff()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<Staff>> searchStaff(
            @RequestParam String name) {

        return ResponseEntity.ok(
                staffService.searchStaff(name)
        );
    }
}
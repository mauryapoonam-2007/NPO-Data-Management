package com.npo.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.npo.management.model.Education;
import com.npo.management.service.EducationService;

@RestController
@RequestMapping("/admin/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(
            EducationService educationService) {

        this.educationService = educationService;
    }

    @PostMapping
    public ResponseEntity<Education> addEducation(
            @RequestBody Education education) {

        return ResponseEntity.ok(
                educationService.addEducation(education)
        );
    }

    @GetMapping
    public ResponseEntity<List<Education>> getAllEducation() {

        return ResponseEntity.ok(
                educationService.getAllEducation()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(
            @PathVariable String id) {

        return ResponseEntity.ok(
                educationService.getEducationById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(
            @PathVariable String id,
            @RequestBody Education education) {

        return ResponseEntity.ok(
                educationService.updateEducation(
                        id, education)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEducation(
            @PathVariable String id) {

        educationService.deleteEducation(id);

        return ResponseEntity.ok(
                "Education record deleted successfully"
        );
    }

    @GetMapping("/active")
    public ResponseEntity<List<Education>>
            getActiveEducation() {

        return ResponseEntity.ok(
                educationService.getActiveEducation()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<Education>>
            searchByChildName(
                    @RequestParam String name) {

        return ResponseEntity.ok(
                educationService.searchByChildName(name)
        );
    }

    @GetMapping("/child/{childId}")
    public ResponseEntity<List<Education>>
            getByChildId(
                    @PathVariable String childId) {

        return ResponseEntity.ok(
                educationService.getByChildId(childId)
        );
    }
}
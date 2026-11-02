package com.npo.management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.npo.management.model.Child;
import com.npo.management.repository.ChildRepository;

@Service
public class ChildService {

    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Child addChild(Child child) {

        child.setStatus("ACTIVE");
        child.setCreatedAt(LocalDateTime.now());
        child.setUpdatedAt(LocalDateTime.now());

        return childRepository.save(child);
    }

    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    public Child getChildById(String id) {
        return childRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Child not found"));
    }

    public Child updateChild(String id, Child updatedChild) {

        Child existingChild = getChildById(id);

        existingChild.setChildName(updatedChild.getChildName());
        existingChild.setDateOfBirth(updatedChild.getDateOfBirth());
        existingChild.setGender(updatedChild.getGender());
        existingChild.setAdmissionDate(updatedChild.getAdmissionDate());
        existingChild.setEducationStatus(updatedChild.getEducationStatus());
        existingChild.setHealthStatus(updatedChild.getHealthStatus());
        existingChild.setCity(updatedChild.getCity());
        existingChild.setStatus(updatedChild.getStatus());
        existingChild.setUpdatedAt(LocalDateTime.now());

        return childRepository.save(existingChild);
    }

    public void deleteChild(String id) {
        childRepository.deleteById(id);
    }

    public List<Child> getActiveChildren() {
        return childRepository.findByStatus("ACTIVE");
    }

    public List<Child> searchChildren(String name) {
        return childRepository.findByChildNameContainingIgnoreCase(name);
    }
}
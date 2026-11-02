package com.npo.management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.npo.management.model.Staff;
import com.npo.management.repository.StaffRepository;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff addStaff(Staff staff) {
        staff.setStatus("ACTIVE");
        staff.setCreatedAt(LocalDateTime.now());
        staff.setUpdatedAt(LocalDateTime.now());

        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(String id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
    }

    public Staff updateStaff(String id, Staff updatedStaff) {

        Staff existingStaff = getStaffById(id);

        existingStaff.setFullName(updatedStaff.getFullName());
        existingStaff.setEmail(updatedStaff.getEmail());
        existingStaff.setPhone(updatedStaff.getPhone());
        existingStaff.setDesignation(updatedStaff.getDesignation());
        existingStaff.setDepartment(updatedStaff.getDepartment());
        existingStaff.setJoiningDate(updatedStaff.getJoiningDate());
        existingStaff.setCity(updatedStaff.getCity());
        existingStaff.setStatus(updatedStaff.getStatus());
        existingStaff.setUpdatedAt(LocalDateTime.now());

        return staffRepository.save(existingStaff);
    }

    public void deleteStaff(String id) {
        staffRepository.deleteById(id);
    }

    public List<Staff> getActiveStaff() {
        return staffRepository.findByStatus("ACTIVE");
    }

    public List<Staff> searchStaff(String name) {
        return staffRepository.findByFullNameContainingIgnoreCase(name);
    }
}
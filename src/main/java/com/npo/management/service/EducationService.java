package com.npo.management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.npo.management.model.Education;
import com.npo.management.repository.EducationRepository;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(
            EducationRepository educationRepository) {

        this.educationRepository = educationRepository;
    }

    public Education addEducation(Education education) {

        education.setStatus("ACTIVE");
        education.setCreatedAt(LocalDateTime.now());
        education.setUpdatedAt(LocalDateTime.now());

        return educationRepository.save(education);
    }

    public List<Education> getAllEducation() {

        return educationRepository.findAll();
    }

    public Education getEducationById(String id) {

        return educationRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Education record not found"));
    }

    public Education updateEducation(
            String id,
            Education updatedEducation) {

        Education existing =
                getEducationById(id);

        existing.setChildId(
                updatedEducation.getChildId());

        existing.setChildName(
                updatedEducation.getChildName());

        existing.setSchoolName(
                updatedEducation.getSchoolName());

        existing.setClassName(
                updatedEducation.getClassName());

        existing.setAcademicYear(
                updatedEducation.getAcademicYear());

        existing.setAdmissionDate(
                updatedEducation.getAdmissionDate());

        existing.setAttendancePercentage(
                updatedEducation.getAttendancePercentage());

        existing.setPerformance(
                updatedEducation.getPerformance());

        existing.setRemarks(
                updatedEducation.getRemarks());

        existing.setStatus(
                updatedEducation.getStatus());

        existing.setUpdatedAt(
                LocalDateTime.now());

        return educationRepository.save(existing);
    }

    public void deleteEducation(String id) {

        educationRepository.deleteById(id);
    }

    public List<Education> getActiveEducation() {

        return educationRepository.findByStatus(
                "ACTIVE");
    }

    public List<Education> searchByChildName(
            String name) {

        return educationRepository
                .findByChildNameContainingIgnoreCase(name);
    }

    public List<Education> getByChildId(
            String childId) {

        return educationRepository
                .findByChildId(childId);
    }
}
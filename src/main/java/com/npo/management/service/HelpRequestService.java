package com.npo.management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.npo.management.model.HelpRequest;
import com.npo.management.repository.HelpRequestRepository;

@Service
public class HelpRequestService {

    private final HelpRequestRepository helpRequestRepository;

    public HelpRequestService(HelpRequestRepository helpRequestRepository) {
        this.helpRequestRepository = helpRequestRepository;
    }

    public HelpRequest createRequest(HelpRequest request) {
        request.setStatus("PENDING");
        request.setCreatedAt(LocalDateTime.now());

        if (request.getPriority() == null ||
            request.getPriority().isBlank()) {
            request.setPriority("MEDIUM");
        }

        return helpRequestRepository.save(request);
    }

    public List<HelpRequest> getAllRequests() {
        return helpRequestRepository.findAll();
    }

    public List<HelpRequest> getPendingRequests() {
        return helpRequestRepository.findByStatus("PENDING");
    }

    public HelpRequest approveRequest(String id) {
        HelpRequest request = helpRequestRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Help request not found"));

        if (!request.getStatus().equals("PENDING")) {
            throw new RuntimeException(
                    "Only PENDING requests can be approved");
        }

        request.setStatus("APPROVED");
        request.setApprovedAt(LocalDateTime.now());

        return helpRequestRepository.save(request);
    }

    public HelpRequest rejectRequest(String id) {
        HelpRequest request = helpRequestRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Help request not found"));

        if (!request.getStatus().equals("PENDING")) {
            throw new RuntimeException(
                    "Only PENDING requests can be rejected");
        }

        request.setStatus("REJECTED");

        return helpRequestRepository.save(request);
    }

    public HelpRequest assignHelper(
            String requestId, String helperId) {

        HelpRequest request = helpRequestRepository.findById(requestId)
                .orElseThrow(() ->
                    new RuntimeException("Help request not found"));

        if (!request.getStatus().equals("APPROVED")) {
            throw new RuntimeException(
                    "Only APPROVED requests can be assigned");
        }

        request.setAssignedHelperId(helperId);
        request.setStatus("ASSIGNED");

        return helpRequestRepository.save(request);
    }

    public List<HelpRequest> searchByCity(String city) {
    return helpRequestRepository.findByCity(city);
}

public List<HelpRequest> searchByCategory(String category) {
    return helpRequestRepository.findByCategory(category);
}

public List<HelpRequest> searchByPriority(String priority) {
    return helpRequestRepository.findByPriority(priority);
}

public List<HelpRequest> searchByStatus(String status) {
    return helpRequestRepository.findByStatus(status);
}
}
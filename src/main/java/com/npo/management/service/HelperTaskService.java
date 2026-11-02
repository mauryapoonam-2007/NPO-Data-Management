package com.npo.management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.npo.management.model.HelpHistory;
import com.npo.management.model.HelpRequest;
import com.npo.management.repository.HelpRequestRepository;

@Service
public class HelperTaskService {

    private final HelpRequestRepository helpRequestRepository;
    private final HelpHistoryService helpHistoryService;
    private final ImpactService impactService;

    public HelperTaskService(
        HelpRequestRepository helpRequestRepository,HelpHistoryService helpHistoryService,ImpactService impactService) {
            this.helpRequestRepository = helpRequestRepository;
            this.helpHistoryService = helpHistoryService;
            this.impactService = impactService;
    }

    public List<HelpRequest> getHelperTasks(String helperId) {

        return helpRequestRepository
                .findByAssignedHelperId(helperId);
    }

    public HelpRequest acceptTask(
            String requestId, String helperId) {

        HelpRequest request = helpRequestRepository
                .findById(requestId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Help request not found"));

        if (!helperId.equals(request.getAssignedHelperId())) {
            throw new RuntimeException(
                "You are not assigned to this task");
        }

        if (!request.getStatus().equals("ASSIGNED")) {
            throw new RuntimeException(
                "Task cannot be accepted");
        }

        request.setStatus("IN_PROGRESS");

        return helpRequestRepository.save(request);
    }

    public HelpRequest completeTask(
            String requestId, String helperId) {

        HelpRequest request = helpRequestRepository
                .findById(requestId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Help request not found"));

        if (!helperId.equals(request.getAssignedHelperId())) {
            throw new RuntimeException(
                "You are not assigned to this task");
        }

        if (!request.getStatus().equals("IN_PROGRESS")) {
            throw new RuntimeException(
                "Task is not in progress");
        }

        request.setStatus("COMPLETED");
        request.setCompletedAt(java.time.LocalDateTime.now());

        HelpHistory history = new HelpHistory();

        history.setRequestId(request.getId());
        history.setBeneficiaryId(request.getBeneficiaryId());
        history.setHelperId(request.getAssignedHelperId());
        history.setTitle(request.getTitle());
        history.setCategory(request.getCategory());
        history.setCity(request.getCity());
        history.setPriority(request.getPriority());
        history.setCompletedAt(request.getCompletedAt());

        helpHistoryService.saveHistory(history);

        impactService.addPoints(
            helperId,
            50
        );
        
        return helpRequestRepository.save(request);
    }
}
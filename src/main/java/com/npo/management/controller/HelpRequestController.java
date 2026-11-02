package com.npo.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.npo.management.model.HelpRequest;
import com.npo.management.service.HelpRequestService;

@RestController
public class HelpRequestController {

    private final HelpRequestService helpRequestService;

    public HelpRequestController(HelpRequestService helpRequestService) {
        this.helpRequestService = helpRequestService;
    }

    @PostMapping("/help-requests")
    public HelpRequest createRequest(
            @RequestBody HelpRequest request) {

        return helpRequestService.createRequest(request);
    }

    @GetMapping("/admin/help-requests")
    public List<HelpRequest> getAllRequests() {
        return helpRequestService.getAllRequests();
    }

    @GetMapping("/admin/help-requests/pending")
    public List<HelpRequest> getPendingRequests() {
        return helpRequestService.getPendingRequests();
    }

    @PutMapping("/admin/help-requests/{id}/approve")
    public HelpRequest approveRequest(
            @PathVariable String id) {

        return helpRequestService.approveRequest(id);
    }

    @PutMapping("/admin/help-requests/{id}/reject")
    public HelpRequest rejectRequest(
            @PathVariable String id) {

        return helpRequestService.rejectRequest(id);
    }

    @PutMapping("/admin/help-requests/{id}/assign")
    public HelpRequest assignHelper(
            @PathVariable String id,
            @RequestParam String helperId) {

        return helpRequestService.assignHelper(id, helperId);
    }

    @GetMapping("/admin/help-requests/search")
public List<HelpRequest> searchHelpRequests(
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String priority,
        @RequestParam(required = false) String status) {

    if (city != null) {
        return helpRequestService.searchByCity(city);
    }

    if (category != null) {
        return helpRequestService.searchByCategory(category);
    }

    if (priority != null) {
        return helpRequestService.searchByPriority(priority);
    }

    if (status != null) {
        return helpRequestService.searchByStatus(status);
    }

    return helpRequestService.getAllRequests();
}
}
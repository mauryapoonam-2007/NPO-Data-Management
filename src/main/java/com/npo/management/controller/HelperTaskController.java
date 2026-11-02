package com.npo.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.npo.management.model.HelpRequest;
import com.npo.management.service.HelperTaskService;

@RestController
public class HelperTaskController {

    private final HelperTaskService helperTaskService;

    public HelperTaskController(
            HelperTaskService helperTaskService) {
        this.helperTaskService = helperTaskService;
    }

    @GetMapping("/helper/tasks/{helperId}")
    public List<HelpRequest> getHelperTasks(
            @PathVariable String helperId) {

        return helperTaskService.getHelperTasks(helperId);
    }

    @PutMapping("/helper/tasks/{requestId}/accept")
    public HelpRequest acceptTask(
            @PathVariable String requestId,
            @RequestParam String helperId) {

        return helperTaskService.acceptTask(
                requestId, helperId);
    }

    @PutMapping("/helper/tasks/{requestId}/complete")
    public HelpRequest completeTask(
            @PathVariable String requestId,
            @RequestParam String helperId) {

        return helperTaskService.completeTask(
                requestId, helperId);
    }
}
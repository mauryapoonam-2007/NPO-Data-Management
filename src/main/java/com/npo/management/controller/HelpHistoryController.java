package com.npo.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.npo.management.model.HelpHistory;
import com.npo.management.service.HelpHistoryService;

@RestController
public class HelpHistoryController {

    private final HelpHistoryService helpHistoryService;

    public HelpHistoryController(
            HelpHistoryService helpHistoryService) {
        this.helpHistoryService = helpHistoryService;
    }

    @GetMapping("/helper/history/{helperId}")
    public List<HelpHistory> getHelperHistory(
            @PathVariable String helperId) {

        return helpHistoryService.getHelperHistory(helperId);
    }

    @GetMapping("/admin/help-history")
    public List<HelpHistory> getAllHistory() {
        return helpHistoryService.getAllHistory();
    }
}

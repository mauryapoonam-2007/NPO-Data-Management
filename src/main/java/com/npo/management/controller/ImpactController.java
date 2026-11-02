package com.npo.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.npo.management.model.HelperImpact;
import com.npo.management.service.ImpactService;

@RestController
public class ImpactController {

    private final ImpactService impactService;

    public ImpactController(ImpactService impactService) {
        this.impactService = impactService;
    }

    @GetMapping("/helper/impact/{helperId}")
    public HelperImpact getHelperImpact(
            @PathVariable String helperId) {

        return impactService.getHelperImpact(helperId);
    }

    @GetMapping("/admin/leaderboard")
    public List<HelperImpact> getAdminLeaderboard() {

        return impactService.getLeaderboard();
    }

    @PostMapping("/admin/impact/create")
    public HelperImpact createImpact(
            @RequestParam String helperId,
            @RequestParam String helperName) {

        return impactService.createImpact(
                helperId,
                helperName);
    }
    @GetMapping("/leaderboard")
public List<HelperImpact> getLeaderboard() {
    return impactService.getLeaderboard();
}

}
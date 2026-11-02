package com.npo.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.npo.management.model.HelperImpact;
import com.npo.management.repository.HelperImpactRepository;

@Service
public class ImpactService {

    private final HelperImpactRepository helperImpactRepository;

    public ImpactService(HelperImpactRepository helperImpactRepository) {
        this.helperImpactRepository = helperImpactRepository;
    }

    public HelperImpact getHelperImpact(String helperId) {

        HelperImpact impact =
                helperImpactRepository.findByHelperId(helperId);

        if (impact == null) {
            throw new RuntimeException("Helper impact not found");
        }

        return impact;
    }

    public List<HelperImpact> getLeaderboard() {
        return helperImpactRepository.findAllByOrderByPointsDesc();
    }

    public HelperImpact createImpact(
            String helperId,
            String helperName) {

        if (helperImpactRepository.findByHelperId(helperId) != null) {
            throw new RuntimeException(
                    "Impact record already exists");
        }

        HelperImpact impact = new HelperImpact();

        impact.setHelperId(helperId);
        impact.setHelperName(helperName);
        impact.setPoints(0);
        impact.setCompletedHelps(0);
        impact.setLevel("BEGINNER");
        impact.setBadge("NEW HELPER");

        return helperImpactRepository.save(impact);
    }

    public HelperImpact addPoints(
            String helperId,
            int points) {

        HelperImpact impact =
                helperImpactRepository.findByHelperId(helperId);

        if (impact == null) {
            throw new RuntimeException(
                    "Helper impact not found");
        }

        impact.setPoints(impact.getPoints() + points);
        impact.setCompletedHelps(
                impact.getCompletedHelps() + 1);

        updateLevelAndBadge(impact);

        return helperImpactRepository.save(impact);
    }

    private void updateLevelAndBadge(HelperImpact impact) {

        int points = impact.getPoints();

        if (points >= 500) {
            impact.setLevel("CHAMPION");
            impact.setBadge("NPO CHAMPION");

        } else if (points >= 250) {
            impact.setLevel("LEADER");
            impact.setBadge("HELP LEADER");

        } else if (points >= 100) {
            impact.setLevel("ACTIVE");
            impact.setBadge("ACTIVE HELPER");

        } else {
            impact.setLevel("BEGINNER");
            impact.setBadge("NEW HELPER");
        }
    }
    
}
package com.npo.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.npo.management.model.HelpHistory;
import com.npo.management.repository.HelpHistoryRepository;

@Service
public class HelpHistoryService {

    private final HelpHistoryRepository helpHistoryRepository;

    public HelpHistoryService(
            HelpHistoryRepository helpHistoryRepository) {

        this.helpHistoryRepository = helpHistoryRepository;
    }

    public HelpHistory saveHistory(HelpHistory history) {
        return helpHistoryRepository.save(history);
    }

    public List<HelpHistory> getHelperHistory(String helperId) {
        return helpHistoryRepository.findByHelperId(helperId);
    }

    public List<HelpHistory> getBeneficiaryHistory(
            String beneficiaryId) {

        return helpHistoryRepository
                .findByBeneficiaryId(beneficiaryId);
    }

    public List<HelpHistory> getAllHistory() {
        return helpHistoryRepository.findAll();
    }
}
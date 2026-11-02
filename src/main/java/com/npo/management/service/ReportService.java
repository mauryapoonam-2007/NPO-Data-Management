package com.npo.management.service;

import org.springframework.stereotype.Service;

import com.npo.management.dto.ReportResponse;
import com.npo.management.repository.HelpRequestRepository;
import com.npo.management.repository.HelpHistoryRepository;
import com.npo.management.repository.InventoryRepository;
import com.npo.management.repository.DonationRepository;
import com.npo.management.model.Donation;

@Service
public class ReportService {

    private final HelpRequestRepository helpRequestRepository;
    private final HelpHistoryRepository helpHistoryRepository;
    private final InventoryRepository inventoryRepository;
    private final DonationRepository donationRepository;

    public ReportService(
            HelpRequestRepository helpRequestRepository,
            HelpHistoryRepository helpHistoryRepository,
            InventoryRepository inventoryRepository,
            DonationRepository donationRepository) {

        this.helpRequestRepository = helpRequestRepository;
        this.helpHistoryRepository = helpHistoryRepository;
        this.inventoryRepository = inventoryRepository;
        this.donationRepository = donationRepository;
    }

    public ReportResponse getOverallReport() {

        var requests = helpRequestRepository.findAll();
        var donations = donationRepository.findAll();
        var inventory = inventoryRepository.findAll();

        long pendingRequests = requests.stream()
                .filter(r -> "PENDING".equals(r.getStatus()))
                .count();

        long approvedRequests = requests.stream()
                .filter(r -> "APPROVED".equals(r.getStatus()))
                .count();

        long assignedRequests = requests.stream()
                .filter(r -> "ASSIGNED".equals(r.getStatus()))
                .count();

        long inProgressRequests = requests.stream()
                .filter(r -> "IN_PROGRESS".equals(r.getStatus()))
                .count();

        long completedRequests = requests.stream()
                .filter(r -> "COMPLETED".equals(r.getStatus()))
                .count();

        long rejectedRequests = requests.stream()
                .filter(r -> "REJECTED".equals(r.getStatus()))
                .count();

        long receivedDonations = donations.stream()
                .filter(d -> "RECEIVED".equals(d.getStatus()))
                .count();

        long rejectedDonations = donations.stream()
                .filter(d -> "REJECTED".equals(d.getStatus()))
                .count();

        double totalMoneyDonated = donations.stream()
                .filter(d -> "MONEY".equals(d.getType()))
                .filter(d -> "RECEIVED".equals(d.getStatus()))
                .filter(d -> d.getAmount() != null)
                .mapToDouble(Donation::getAmount)
                .sum();

        long totalMaterialDonations = donations.stream()
                .filter(d -> "MATERIAL".equals(d.getType()))
                .filter(d -> "RECEIVED".equals(d.getStatus()))
                .count();

        long totalInventoryQuantity = inventory.stream()
                .mapToLong(i -> i.getQuantity())
                .sum();

        return new ReportResponse(
                requests.size(),
                pendingRequests,
                approvedRequests,
                assignedRequests,
                inProgressRequests,
                completedRequests,
                rejectedRequests,
                donations.size(),
                receivedDonations,
                rejectedDonations,
                totalMoneyDonated,
                totalMaterialDonations,
                inventory.size(),
                totalInventoryQuantity,
                helpHistoryRepository.count()
        );
    }
}
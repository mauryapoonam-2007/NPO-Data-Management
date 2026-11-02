package com.npo.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.npo.management.dto.DashboardResponse;
import com.npo.management.model.Donation;
import com.npo.management.model.HelpRequest;
import com.npo.management.model.Inventory;
import com.npo.management.repository.DonationRepository;
import com.npo.management.repository.HelpRequestRepository;
import com.npo.management.repository.HelpHistoryRepository;
import com.npo.management.repository.InventoryRepository;
import com.npo.management.repository.UserRepository;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final HelpRequestRepository helpRequestRepository;
    private final HelpHistoryRepository helpHistoryRepository;
    private final InventoryRepository inventoryRepository;
    private final DonationRepository donationRepository;

    public DashboardService(
            UserRepository userRepository,
            HelpRequestRepository helpRequestRepository,
            HelpHistoryRepository helpHistoryRepository,
            InventoryRepository inventoryRepository,
            DonationRepository donationRepository) {

        this.userRepository = userRepository;
        this.helpRequestRepository = helpRequestRepository;
        this.helpHistoryRepository = helpHistoryRepository;
        this.inventoryRepository = inventoryRepository;
        this.donationRepository = donationRepository;
    }

    public DashboardResponse getDashboard() {

        DashboardResponse dashboard = new DashboardResponse();

        // Users
        List<com.npo.management.model.User> users =
                userRepository.findAll();

        dashboard.setTotalUsers(users.size());

        dashboard.setTotalHelpers(
                users.stream()
                        .filter(user -> "HELPER".equals(user.getRole()))
                        .count()
        );

        dashboard.setTotalBeneficiaries(
                users.stream()
                        .filter(user -> "BENEFICIARY".equals(user.getRole()))
                        .count()
        );

        dashboard.setTotalAdmins(
                users.stream()
                        .filter(user -> "ADMIN".equals(user.getRole()))
                        .count()
        );

        // Help Requests
        List<HelpRequest> requests =
                helpRequestRepository.findAll();

        dashboard.setTotalRequests(requests.size());

        dashboard.setPendingRequests(
                countByStatus(requests, "PENDING")
        );

        dashboard.setApprovedRequests(
                countByStatus(requests, "APPROVED")
        );

        dashboard.setAssignedRequests(
                countByStatus(requests, "ASSIGNED")
        );

        dashboard.setInProgressRequests(
                countByStatus(requests, "IN_PROGRESS")
        );

        dashboard.setCompletedRequests(
                countByStatus(requests, "COMPLETED")
        );

        dashboard.setRejectedRequests(
                countByStatus(requests, "REJECTED")
        );

        // Help History
        dashboard.setTotalCompletedHelps(
                helpHistoryRepository.count()
        );

        // Inventory
        List<Inventory> inventory =
                inventoryRepository.findAll();

        dashboard.setTotalInventoryItems(
                inventory.size()
        );

        long totalQuantity = inventory.stream()
                .mapToLong(Inventory::getQuantity)
                .sum();

        dashboard.setTotalInventoryQuantity(totalQuantity);

        // Donations
        List<Donation> donations =
                donationRepository.findAll();

        dashboard.setTotalDonations(donations.size());

        dashboard.setPendingDonations(
                countDonationStatus(donations, "PENDING")
        );

        dashboard.setReceivedDonations(
                countDonationStatus(donations, "RECEIVED")
        );

        dashboard.setRejectedDonations(
                countDonationStatus(donations, "REJECTED")
        );

        double totalMoney = donations.stream()
                .filter(donation ->
                        "MONEY".equals(donation.getType()) &&
                        "RECEIVED".equals(donation.getStatus()))
                .mapToDouble(Donation::getAmount)
                .sum();

        dashboard.setTotalMoneyDonated(totalMoney);

        long materialDonations = donations.stream()
                .filter(donation ->
                        "MATERIAL".equals(donation.getType()) &&
                        "RECEIVED".equals(donation.getStatus()))
                .count();

        dashboard.setTotalMaterialDonations(materialDonations);

        return dashboard;
    }

    private long countByStatus(
            List<HelpRequest> requests,
            String status) {

        return requests.stream()
                .filter(request ->
                        status.equals(request.getStatus()))
                .count();
    }

    private long countDonationStatus(
            List<Donation> donations,
            String status) {

        return donations.stream()
                .filter(donation ->
                        status.equals(donation.getStatus()))
                .count();
    }
}
package com.npo.management.dto;

public class DashboardResponse {

    private long totalUsers;
    private long totalHelpers;
    private long totalBeneficiaries;
    private long totalAdmins;

    private long totalRequests;
    private long pendingRequests;
    private long approvedRequests;
    private long assignedRequests;
    private long inProgressRequests;
    private long completedRequests;
    private long rejectedRequests;

    private long totalCompletedHelps;

    private long totalInventoryItems;
    private long totalInventoryQuantity;

    private long totalDonations;
    private long pendingDonations;
    private long receivedDonations;
    private long rejectedDonations;

    private double totalMoneyDonated;
    private long totalMaterialDonations;

    public DashboardResponse() {
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalHelpers() {
        return totalHelpers;
    }

    public void setTotalHelpers(long totalHelpers) {
        this.totalHelpers = totalHelpers;
    }

    public long getTotalBeneficiaries() {
        return totalBeneficiaries;
    }

    public void setTotalBeneficiaries(long totalBeneficiaries) {
        this.totalBeneficiaries = totalBeneficiaries;
    }

    public long getTotalAdmins() {
        return totalAdmins;
    }

    public void setTotalAdmins(long totalAdmins) {
        this.totalAdmins = totalAdmins;
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(long totalRequests) {
        this.totalRequests = totalRequests;
    }

    public long getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(long pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public long getApprovedRequests() {
        return approvedRequests;
    }

    public void setApprovedRequests(long approvedRequests) {
        this.approvedRequests = approvedRequests;
    }

    public long getAssignedRequests() {
        return assignedRequests;
    }

    public void setAssignedRequests(long assignedRequests) {
        this.assignedRequests = assignedRequests;
    }

    public long getInProgressRequests() {
        return inProgressRequests;
    }

    public void setInProgressRequests(long inProgressRequests) {
        this.inProgressRequests = inProgressRequests;
    }

    public long getCompletedRequests() {
        return completedRequests;
    }

    public void setCompletedRequests(long completedRequests) {
        this.completedRequests = completedRequests;
    }

    public long getRejectedRequests() {
        return rejectedRequests;
    }

    public void setRejectedRequests(long rejectedRequests) {
        this.rejectedRequests = rejectedRequests;
    }

    public long getTotalCompletedHelps() {
        return totalCompletedHelps;
    }

    public void setTotalCompletedHelps(long totalCompletedHelps) {
        this.totalCompletedHelps = totalCompletedHelps;
    }

    public long getTotalInventoryItems() {
        return totalInventoryItems;
    }

    public void setTotalInventoryItems(long totalInventoryItems) {
        this.totalInventoryItems = totalInventoryItems;
    }

    public long getTotalInventoryQuantity() {
        return totalInventoryQuantity;
    }

    public void setTotalInventoryQuantity(long totalInventoryQuantity) {
        this.totalInventoryQuantity = totalInventoryQuantity;
    }

    public long getTotalDonations() {
        return totalDonations;
    }

    public void setTotalDonations(long totalDonations) {
        this.totalDonations = totalDonations;
    }

    public long getPendingDonations() {
        return pendingDonations;
    }

    public void setPendingDonations(long pendingDonations) {
        this.pendingDonations = pendingDonations;
    }

    public long getReceivedDonations() {
        return receivedDonations;
    }

    public void setReceivedDonations(long receivedDonations) {
        this.receivedDonations = receivedDonations;
    }

    public long getRejectedDonations() {
        return rejectedDonations;
    }

    public void setRejectedDonations(long rejectedDonations) {
        this.rejectedDonations = rejectedDonations;
    }

    public double getTotalMoneyDonated() {
        return totalMoneyDonated;
    }

    public void setTotalMoneyDonated(double totalMoneyDonated) {
        this.totalMoneyDonated = totalMoneyDonated;
    }

    public long getTotalMaterialDonations() {
        return totalMaterialDonations;
    }

    public void setTotalMaterialDonations(long totalMaterialDonations) {
        this.totalMaterialDonations = totalMaterialDonations;
    }
}

package com.npo.management.dto;

public class ReportResponse {

    private long totalRequests;
    private long pendingRequests;
    private long approvedRequests;
    private long assignedRequests;
    private long inProgressRequests;
    private long completedRequests;
    private long rejectedRequests;

    private long totalDonations;
    private long receivedDonations;
    private long rejectedDonations;
    private double totalMoneyDonated;
    private long totalMaterialDonations;

    private long totalInventoryItems;
    private long totalInventoryQuantity;

    private long totalCompletedHelps;

    public ReportResponse() {
    }

    public ReportResponse(
            long totalRequests,
            long pendingRequests,
            long approvedRequests,
            long assignedRequests,
            long inProgressRequests,
            long completedRequests,
            long rejectedRequests,
            long totalDonations,
            long receivedDonations,
            long rejectedDonations,
            double totalMoneyDonated,
            long totalMaterialDonations,
            long totalInventoryItems,
            long totalInventoryQuantity,
            long totalCompletedHelps) {

        this.totalRequests = totalRequests;
        this.pendingRequests = pendingRequests;
        this.approvedRequests = approvedRequests;
        this.assignedRequests = assignedRequests;
        this.inProgressRequests = inProgressRequests;
        this.completedRequests = completedRequests;
        this.rejectedRequests = rejectedRequests;
        this.totalDonations = totalDonations;
        this.receivedDonations = receivedDonations;
        this.rejectedDonations = rejectedDonations;
        this.totalMoneyDonated = totalMoneyDonated;
        this.totalMaterialDonations = totalMaterialDonations;
        this.totalInventoryItems = totalInventoryItems;
        this.totalInventoryQuantity = totalInventoryQuantity;
        this.totalCompletedHelps = totalCompletedHelps;
    }

    public long getTotalRequests() { return totalRequests; }
    public long getPendingRequests() { return pendingRequests; }
    public long getApprovedRequests() { return approvedRequests; }
    public long getAssignedRequests() { return assignedRequests; }
    public long getInProgressRequests() { return inProgressRequests; }
    public long getCompletedRequests() { return completedRequests; }
    public long getRejectedRequests() { return rejectedRequests; }

    public long getTotalDonations() { return totalDonations; }
    public long getReceivedDonations() { return receivedDonations; }
    public long getRejectedDonations() { return rejectedDonations; }
    public double getTotalMoneyDonated() { return totalMoneyDonated; }
    public long getTotalMaterialDonations() { return totalMaterialDonations; }

    public long getTotalInventoryItems() { return totalInventoryItems; }
    public long getTotalInventoryQuantity() { return totalInventoryQuantity; }

    public long getTotalCompletedHelps() { return totalCompletedHelps; }

    public void setTotalRequests(long totalRequests) {
        this.totalRequests = totalRequests;
    }

    public void setPendingRequests(long pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public void setApprovedRequests(long approvedRequests) {
        this.approvedRequests = approvedRequests;
    }

    public void setAssignedRequests(long assignedRequests) {
        this.assignedRequests = assignedRequests;
    }

    public void setInProgressRequests(long inProgressRequests) {
        this.inProgressRequests = inProgressRequests;
    }

    public void setCompletedRequests(long completedRequests) {
        this.completedRequests = completedRequests;
    }

    public void setRejectedRequests(long rejectedRequests) {
        this.rejectedRequests = rejectedRequests;
    }

    public void setTotalDonations(long totalDonations) {
        this.totalDonations = totalDonations;
    }

    public void setReceivedDonations(long receivedDonations) {
        this.receivedDonations = receivedDonations;
    }

    public void setRejectedDonations(long rejectedDonations) {
        this.rejectedDonations = rejectedDonations;
    }

    public void setTotalMoneyDonated(double totalMoneyDonated) {
        this.totalMoneyDonated = totalMoneyDonated;
    }

    public void setTotalMaterialDonations(long totalMaterialDonations) {
        this.totalMaterialDonations = totalMaterialDonations;
    }

    public void setTotalInventoryItems(long totalInventoryItems) {
        this.totalInventoryItems = totalInventoryItems;
    }

    public void setTotalInventoryQuantity(long totalInventoryQuantity) {
        this.totalInventoryQuantity = totalInventoryQuantity;
    }

    public void setTotalCompletedHelps(long totalCompletedHelps) {
        this.totalCompletedHelps = totalCompletedHelps;
    }
}
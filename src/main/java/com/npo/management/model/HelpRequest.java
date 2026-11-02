package com.npo.management.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "help_requests")
public class HelpRequest {

    @Id
    private String id;

    private String beneficiaryId;
    private String title;
    private String description;
    private String category;
    private String city;
    private String priority;
    private String status;
    private LocalDateTime createdAt;
    private String assignedHelperId;
    private LocalDateTime approvedAt;
    private LocalDateTime completedAt;

    public HelpRequest() {
    }

    public HelpRequest(String id, String beneficiaryId,
                       String title, String description,
                       String category, String city,
                       String priority, String status,
                       LocalDateTime createdAt,String assignedHelperId,LocalDateTime approvedAt,
                       LocalDateTime completedAt) {
        this.id = id;
        this.beneficiaryId = beneficiaryId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.city = city;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.assignedHelperId=assignedHelperId;
        this.approvedAt=approvedAt;
        this.completedAt=completedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAssignedHelperId() {
    return assignedHelperId;
    }

    public void setAssignedHelperId(String assignedHelperId) {
        this.assignedHelperId = assignedHelperId;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }


    
}
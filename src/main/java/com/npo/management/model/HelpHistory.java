package com.npo.management.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "help_history")
public class HelpHistory {

    @Id
    private String id;

    private String requestId;
    private String beneficiaryId;
    private String helperId;
    private String title;
    private String category;
    private String city;
    private String priority;
    private LocalDateTime completedAt;

    public HelpHistory() {
    }

    public HelpHistory(String id, String requestId,
                       String beneficiaryId, String helperId,
                       String title, String category,
                       String city, String priority,
                       LocalDateTime completedAt) {

        this.id = id;
        this.requestId = requestId;
        this.beneficiaryId = beneficiaryId;
        this.helperId = helperId;
        this.title = title;
        this.category = category;
        this.city = city;
        this.priority = priority;
        this.completedAt = completedAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getHelperId() {
        return helperId;
    }

    public void setHelperId(String helperId) {
        this.helperId = helperId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
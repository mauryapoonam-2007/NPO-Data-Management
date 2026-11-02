package com.npo.management.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "donations")
public class Donation {

    @Id
    private String id;

    private String donorName;
    private String donorEmail;
    private String donorPhone;

    private String type;

    private Double amount;

    private String itemName;
    private Integer quantity;
    private String unit;

    private String category;
    private String city;
    private String description;

    private String status;

    private LocalDateTime donatedAt;
    private LocalDateTime verifiedAt;

    public Donation() {
    }

    public Donation(String id, String donorName,
                    String donorEmail, String donorPhone,
                    String type, Double amount,
                    String itemName, Integer quantity,
                    String unit, String category,
                    String city, String description,
                    String status,
                    LocalDateTime donatedAt,
                    LocalDateTime verifiedAt) {

        this.id = id;
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorPhone = donorPhone;
        this.type = type;
        this.amount = amount;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
        this.city = city;
        this.description = description;
        this.status = status;
        this.donatedAt = donatedAt;
        this.verifiedAt = verifiedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDonatedAt() {
        return donatedAt;
    }

    public void setDonatedAt(LocalDateTime donatedAt) {
        this.donatedAt = donatedAt;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}

package com.npo.management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "helper_impacts")
public class HelperImpact {

    @Id
    private String id;

    private String helperId;
    private String helperName;
    private int points;
    private int completedHelps;
    private String level;
    private String badge;

    public HelperImpact() {
    }

    public HelperImpact(String id, String helperId, String helperName,
                        int points, int completedHelps,
                        String level, String badge) {
        this.id = id;
        this.helperId = helperId;
        this.helperName = helperName;
        this.points = points;
        this.completedHelps = completedHelps;
        this.level = level;
        this.badge = badge;
    }

    public String getId() {
        return id;
    }

    public String getHelperId() {
        return helperId;
    }

    public String getHelperName() {
        return helperName;
    }

    public int getPoints() {
        return points;
    }

    public int getCompletedHelps() {
        return completedHelps;
    }

    public String getLevel() {
        return level;
    }

    public String getBadge() {
        return badge;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHelperId(String helperId) {
        this.helperId = helperId;
    }

    public void setHelperName(String helperName) {
        this.helperName = helperName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setCompletedHelps(int completedHelps) {
        this.completedHelps = completedHelps;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}
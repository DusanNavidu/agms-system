package com.ijse.automationservice.dto;

import lombok.Data;

/**
 * @author Dusan
 * @date 3/11/2026
 */

public class ZoneDTO {
    private String id;
    private Double minTemp;
    private Double maxTemp;

    // --- Getters and Setters ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }
}
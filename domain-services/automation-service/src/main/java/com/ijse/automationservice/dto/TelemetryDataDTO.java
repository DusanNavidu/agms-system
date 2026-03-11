package com.ijse.automationservice.dto;

import lombok.Data;

/**
 * @author Dusan
 * @date 3/11/2026
 */

public class TelemetryDataDTO {
    private String zoneId;
    private Double temperature;

    // --- Getters and Setters ---
    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
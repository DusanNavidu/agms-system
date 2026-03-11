package com.ijse.automationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Entity
@Table(name = "action_logs")
public class ActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String zoneId;
    private Double recordedTemperature;
    private String actionTaken;
    private LocalDateTime timestamp;

    // --- Constructors ---
    public ActionLog() {
    }

    // --- Getters and Setters ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public Double getRecordedTemperature() {
        return recordedTemperature;
    }

    public void setRecordedTemperature(Double recordedTemperature) {
        this.recordedTemperature = recordedTemperature;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

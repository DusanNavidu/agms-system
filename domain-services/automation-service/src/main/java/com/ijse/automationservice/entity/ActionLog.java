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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String zoneId;
    private Double recordedTemperature;
    private String actionTaken; // "TURN_FAN_ON" or "TURN_HEATER_ON"
    private LocalDateTime timestamp;
}

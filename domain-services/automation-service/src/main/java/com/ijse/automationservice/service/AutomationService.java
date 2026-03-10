package com.ijse.automationservice.service;

import com.ijse.automationservice.client.ZoneServiceClient;
import com.ijse.automationservice.dto.TelemetryDataDTO;
import com.ijse.automationservice.dto.ZoneDTO;
import com.ijse.automationservice.entity.ActionLog;
import com.ijse.automationservice.repository.ActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Service
public class AutomationService {

    @Autowired
    private ActionLogRepository actionLogRepository;

    @Autowired
    private ZoneServiceClient zoneServiceClient;

    public void processTelemetryData(TelemetryDataDTO telemetryData) {
        try {
            ZoneDTO zone = zoneServiceClient.getZoneById(telemetryData.getZoneId());

            if (zone != null) {
                double currentTemp = telemetryData.getTemperature();
                String action = null;

                if (currentTemp > zone.getMaxTemp()) {
                    action = "TURN_FAN_ON"; // Rule 1
                } else if (currentTemp < zone.getMinTemp()) {
                    action = "TURN_HEATER_ON"; // Rule 2
                }

                if (action != null) {
                    ActionLog log = new ActionLog();
                    log.setZoneId(zone.getId());
                    log.setRecordedTemperature(currentTemp);
                    log.setActionTaken(action);
                    log.setTimestamp(LocalDateTime.now());

                    actionLogRepository.save(log);
                    System.out.println("ACTION TRIGGERED: " + action + " for Zone: " + zone.getId());
                } else {
                    System.out.println("Temperature is normal. No action required for Zone: " + zone.getId());
                }
            }
        } catch (Exception e) {
            System.err.println("Error processing telemetry data: " + e.getMessage());
        }
    }

    public List<ActionLog> getAllLogs() {
        return actionLogRepository.findAll();
    }
}

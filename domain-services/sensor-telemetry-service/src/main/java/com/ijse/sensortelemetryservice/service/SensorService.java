package com.ijse.sensortelemetryservice.service;

import com.ijse.sensortelemetryservice.client.AutomationClient;
import com.ijse.sensortelemetryservice.client.IotTelemetryClient;
import com.ijse.sensortelemetryservice.dto.TelemetryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Service
public class SensorService {

    @Autowired
    private IotTelemetryClient iotTelemetryClient;

    @Autowired
    private AutomationClient automationClient;

    private TelemetryResponseDTO latestReading;

    private final String DEVICE_ID = "b751b8c9-644a-484c-ba3f-be63f9b27ad0";

    private final String DUMMY_TOKEN = "Bearer YOUR_TEST_TOKEN";

    @Scheduled(fixedRate = 10000)
    public void fetchAndPushTelemetry() {
        try {
            latestReading = iotTelemetryClient.getDeviceTelemetry(DUMMY_TOKEN, DEVICE_ID);

            System.out.println("Fetched Telemetry: Temp=" + latestReading.getTemperature());

            automationClient.sendDataToAutomation(latestReading);

        } catch (Exception e) {
            System.err.println("Error fetching or pushing telemetry: " + e.getMessage());
        }
    }

    public TelemetryResponseDTO getLatestReading() {
        return latestReading;
    }
}
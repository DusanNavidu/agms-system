package com.ijse.sensortelemetryservice.service;

import com.ijse.sensortelemetryservice.client.AutomationClient;
import com.ijse.sensortelemetryservice.client.IotAuthClient;
import com.ijse.sensortelemetryservice.client.IotTelemetryClient;
import com.ijse.sensortelemetryservice.dto.AuthRequestDTO;
import com.ijse.sensortelemetryservice.dto.AuthResponseDTO;
import com.ijse.sensortelemetryservice.dto.TelemetryResponseDTO;
import feign.FeignException;
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

    @Autowired
    private IotAuthClient iotAuthClient;

    private TelemetryResponseDTO latestReading;
    private String currentAccessToken = null;

    private final String DEVICE_ID = "b751b8c9-644a-484c-ba3f-be63f9b27ad0";

    private final String IOT_USERNAME = "username";
    private final String IOT_PASSWORD = "123456";

    private void authenticate() {
        try {
            System.out.println("Attempting to authenticate with External IoT API...");
            AuthRequestDTO authRequest = new AuthRequestDTO();
            authRequest.setUsername(IOT_USERNAME);
            authRequest.setPassword(IOT_PASSWORD);

            AuthResponseDTO response = iotAuthClient.login(authRequest);
            currentAccessToken = "Bearer " + response.getAccessToken();
            System.out.println("Successfully authenticated! New token acquired.");
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
        }
    }

    @Scheduled(fixedRate = 10000)
    public void fetchAndPushTelemetry() {
        if (currentAccessToken == null) {
            authenticate();
        }

        if (currentAccessToken != null) {
            try {
                latestReading = iotTelemetryClient.getDeviceTelemetry(currentAccessToken, DEVICE_ID);

                if (latestReading != null && latestReading.getTemperature() != null) {
                    System.out.println("📊 Fetched Telemetry: Temp = " + latestReading.getTemperature() + "°C");

                    automationClient.sendDataToAutomation(latestReading);
                } else {
                    System.out.println("⏳ No telemetry data available right now for device: " + DEVICE_ID);
                }

            } catch (FeignException.Unauthorized | FeignException.Forbidden e) {
                System.err.println("⚠️ Token expired or invalid. Will re-authenticate in the next cycle.");
                currentAccessToken = null;
            } catch (Exception e) {
                System.err.println("❌ Error fetching or pushing telemetry: " + e.getMessage());
            }
        }
    }

    public TelemetryResponseDTO getLatestReading() {
        return latestReading;
    }
}
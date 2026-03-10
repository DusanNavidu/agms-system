package com.ijse.sensortelemetryservice.client;

import com.ijse.sensortelemetryservice.dto.TelemetryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@FeignClient(name = "automation-service")
public interface AutomationClient {

    @PostMapping("/api/automation/process")
    void sendDataToAutomation(@RequestBody TelemetryResponseDTO data);
}
package com.ijse.sensortelemetryservice.client;

import com.ijse.sensortelemetryservice.dto.TelemetryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@FeignClient(name = "external-iot-telemetry", url = "http://104.211.95.241:8080/api")
public interface IotTelemetryClient {

    @GetMapping("/devices/telemetry/{deviceId}")
    TelemetryResponseDTO getDeviceTelemetry(
            @RequestHeader("Authorization") String token,
            @PathVariable("deviceId") String deviceId
    );
}
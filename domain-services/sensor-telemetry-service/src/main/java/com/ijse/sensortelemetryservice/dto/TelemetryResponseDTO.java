package com.ijse.sensortelemetryservice.dto;

import lombok.Data;
/**
 * @author Dusan
 * @date 3/11/2026
 */

@Data
public class TelemetryResponseDTO {
    private String deviceId;
    private String zoneId;
    private Double temperature;
    private String tempUnit;
    private Double humidity;
    private String humidityUnit;
    private String capturedAt;
}
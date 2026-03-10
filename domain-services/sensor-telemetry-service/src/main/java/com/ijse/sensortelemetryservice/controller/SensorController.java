package com.ijse.sensortelemetryservice.controller;

import com.ijse.sensortelemetryservice.dto.TelemetryResponseDTO;
import com.ijse.sensortelemetryservice.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    // අන්තිමට ආපු දත්ත පෙන්නන endpoint එක
    @GetMapping("/latest")
    public ResponseEntity<TelemetryResponseDTO> getLatestReading() {
        TelemetryResponseDTO latest = sensorService.getLatestReading();
        if (latest != null) {
            return ResponseEntity.ok(latest);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
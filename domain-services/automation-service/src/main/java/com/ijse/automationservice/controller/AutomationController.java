package com.ijse.automationservice.controller;

import com.ijse.automationservice.dto.TelemetryDataDTO;
import com.ijse.automationservice.entity.ActionLog;
import com.ijse.automationservice.service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@RestController
@RequestMapping("/api/automation")
public class AutomationController {

    @Autowired
    private AutomationService automationService;

    @PostMapping("/process")
    public ResponseEntity<String> receiveTelemetryData(@RequestBody TelemetryDataDTO data) {
        automationService.processTelemetryData(data);
        return ResponseEntity.ok("Data processed successfully");
    }

    @GetMapping("/logs")
    public ResponseEntity<List<ActionLog>> getAutomationLogs() {
        List<ActionLog> logs = automationService.getAllLogs();
        return ResponseEntity.ok(logs);
    }
}

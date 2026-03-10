package com.ijse.zoneservice.controller;

import com.ijse.zoneservice.entity.Zone;
import com.ijse.zoneservice.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping
    public ResponseEntity<?> createZone(
            @RequestHeader("Authorization") String token,
            @RequestBody Zone zone) {
        try {
            Zone createdZone = zoneService.createZone(zone, token);
            return ResponseEntity.ok(createdZone);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getZone(@PathVariable String id) {
        return zoneService.getZoneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateZone(@PathVariable String id, @RequestBody Zone zone) {
        try {
            Zone updatedZone = zoneService.updateZone(id, zone);
            return ResponseEntity.ok(updatedZone);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteZone(@PathVariable String id) {
        zoneService.deleteZone(id);
        return ResponseEntity.ok().build();
    }
}
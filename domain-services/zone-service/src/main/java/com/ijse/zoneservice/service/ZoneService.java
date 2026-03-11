package com.ijse.zoneservice.service;

import com.ijse.zoneservice.client.IotDeviceClient;
import com.ijse.zoneservice.dto.DeviceRequestDTO;
import com.ijse.zoneservice.dto.DeviceResponseDTO;
import com.ijse.zoneservice.entity.Zone;
import com.ijse.zoneservice.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private IotDeviceClient iotDeviceClient;

    public Zone createZone(Zone zone, String token) {
        if (zone.getMinTemp() >= zone.getMaxTemp()) {
            throw new IllegalArgumentException("Minimum temperature must be strictly less than maximum temperature.");
        }

        Zone savedZone = zoneRepository.save(zone);

        DeviceRequestDTO deviceRequest = new DeviceRequestDTO();
        deviceRequest.setName(savedZone.getName() + "-Sensor");
        deviceRequest.setZoneId(savedZone.getId());

        try {
            DeviceResponseDTO response = iotDeviceClient.registerDevice(token, deviceRequest);

            savedZone.setDeviceId(response.getDeviceId());
            return zoneRepository.save(savedZone);

        } catch (Exception e) {
            zoneRepository.delete(savedZone);
            throw new RuntimeException("Failed to register device with External IoT API: " + e.getMessage());
        }
    }

    public Optional<Zone> getZoneById(String id) {
        return zoneRepository.findById(id);
    }

    public Zone updateZone(String id, Zone updatedZone) {
        return zoneRepository.findById(id).map(zone -> {
            if (updatedZone.getMinTemp() >= updatedZone.getMaxTemp()) {
                throw new IllegalArgumentException("Minimum temperature must be strictly less than maximum temperature.");
            }
            zone.setName(updatedZone.getName());
            zone.setMinTemp(updatedZone.getMinTemp());
            zone.setMaxTemp(updatedZone.getMaxTemp());
            return zoneRepository.save(zone);
        }).orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    public void deleteZone(String id) {
        zoneRepository.deleteById(id);
    }
}
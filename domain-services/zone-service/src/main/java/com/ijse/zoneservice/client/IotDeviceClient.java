package com.ijse.zoneservice.client;

import com.ijse.zoneservice.dto.DeviceRequestDTO;
import com.ijse.zoneservice.dto.DeviceResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@FeignClient(name = "external-iot-service", url = "http://104.211.95.241:8080/api")
public interface IotDeviceClient {

    @PostMapping("/devices")
    DeviceResponseDTO registerDevice(
            @RequestHeader("Authorization") String token,
            @RequestBody DeviceRequestDTO request
    );
}

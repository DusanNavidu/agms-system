package com.ijse.sensortelemetryservice.client;

import com.ijse.sensortelemetryservice.dto.AuthRequestDTO;
import com.ijse.sensortelemetryservice.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@FeignClient(name = "external-iot-auth", url = "http://104.211.95.241:8080/api")
public interface IotAuthClient {

    // Login වෙන Endpoint එක
    @PostMapping("/auth/login")
    AuthResponseDTO login(@RequestBody AuthRequestDTO request);
}

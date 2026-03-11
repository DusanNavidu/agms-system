package com.ijse.sensortelemetryservice.dto;

import lombok.Data;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Data
public class AuthResponseDTO {
    private String username;
    private String accessToken;
    private String refreshToken;
}

package com.ijse.automationservice.client;

import com.ijse.automationservice.dto.ZoneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@FeignClient(name = "zone-service")
public interface ZoneServiceClient {

    // Zone එකේ min/max උෂ්ණත්ව සීමාවන් ලබාගන්නා endpoint එක
    @GetMapping("/api/zones/{id}")
    ZoneDTO getZoneById(@PathVariable("id") String id);
}
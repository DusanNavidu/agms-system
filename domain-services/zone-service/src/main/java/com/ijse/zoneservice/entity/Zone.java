package com.ijse.zoneservice.entity;

import jakarta.persistence.*;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Entity
@Table(name = "zones")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private Double minTemp;
    private Double maxTemp;

    private String deviceId;
}

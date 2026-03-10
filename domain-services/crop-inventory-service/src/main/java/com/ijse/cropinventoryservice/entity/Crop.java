package com.ijse.cropinventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Entity
@Table(name = "crops")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String cropType;
    private String zoneId;
    private LocalDate plantDate;

    @Enumerated(EnumType.STRING)
    private CropState state;
}
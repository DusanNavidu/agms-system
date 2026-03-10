package com.ijse.zoneservice.repository;

import com.ijse.zoneservice.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Repository
public interface ZoneRepository extends JpaRepository<Zone, String> {
}
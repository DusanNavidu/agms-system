package com.ijse.automationservice.repository;

import com.ijse.automationservice.entity.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, String> {
}
package com.ijse.cropinventoryservice.repository;

import com.ijse.cropinventoryservice.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Repository
public interface CropRepository extends JpaRepository<Crop, String> {
}

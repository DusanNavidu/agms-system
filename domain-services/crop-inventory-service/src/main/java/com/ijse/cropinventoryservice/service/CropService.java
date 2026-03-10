package com.ijse.cropinventoryservice.service;

import com.ijse.cropinventoryservice.entity.Crop;
import com.ijse.cropinventoryservice.entity.CropState;
import com.ijse.cropinventoryservice.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    public Crop addCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public Crop updateCropState(String id, CropState newState) {
        return cropRepository.findById(id).map(crop -> {
            crop.setState(newState);
            return cropRepository.save(crop);
        }).orElseThrow(() -> new RuntimeException("Crop not found"));
    }

    public void deleteCrop(String id) {
        cropRepository.deleteById(id);
    }
}

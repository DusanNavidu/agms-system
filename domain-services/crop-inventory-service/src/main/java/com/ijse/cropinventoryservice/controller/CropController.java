package com.ijse.cropinventoryservice.controller;

import com.ijse.cropinventoryservice.entity.Crop;
import com.ijse.cropinventoryservice.entity.CropState;
import com.ijse.cropinventoryservice.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dusan
 * @date 3/11/2026
 */

@RestController
@RequestMapping("/api/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping
    public ResponseEntity<Crop> addCrop(@RequestBody Crop crop) {
        return ResponseEntity.ok(cropService.addCrop(crop));
    }

    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops() {
        return ResponseEntity.ok(cropService.getAllCrops());
    }

    @PatchMapping("/{id}/state")
    public ResponseEntity<Crop> updateState(
            @PathVariable String id,
            @RequestParam CropState state) {
        return ResponseEntity.ok(cropService.updateCropState(id, state));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable String id) {
        cropService.deleteCrop(id);
        return ResponseEntity.ok().build();
    }
}

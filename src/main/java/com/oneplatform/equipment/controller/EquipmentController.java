package com.oneplatform.equipment.controller;

import com.oneplatform.equipment.entity.Equipment;
import com.oneplatform.equipment.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipments")
@AllArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping("/{equipmentNumber}")
    public ResponseEntity<Equipment> findEquipmentByNumber(@PathVariable String equipmentNumber) {
        return Optional.ofNullable(equipmentService.findEquipmentByNumber(equipmentNumber))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }



    @GetMapping
    public ResponseEntity<List<Equipment>> findAllEquipments() {
        return Optional.ofNullable(equipmentService.findAllEquipments())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}

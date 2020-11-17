package com.oneplatform.equipment.service;

import com.oneplatform.equipment.entity.Equipment;
import com.oneplatform.equipment.repository.EquipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public Equipment findEquipmentByNumber(String equipmentNumber) {
        return equipmentRepository.findByEquipmentNumber(equipmentNumber);
    }

    public List<Equipment> findAllEquipments() {
        return equipmentRepository.findAll();
    }
}

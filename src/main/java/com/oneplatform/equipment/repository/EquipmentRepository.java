package com.oneplatform.equipment.repository;

import com.oneplatform.equipment.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;


public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findByEquipmentNumber(String equipmentNumber);
}

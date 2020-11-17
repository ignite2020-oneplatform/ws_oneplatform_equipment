package com.oneplatform.equipment.repository;

import com.oneplatform.equipment.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findByEquipmentNumber(String equipmentNumber);
}

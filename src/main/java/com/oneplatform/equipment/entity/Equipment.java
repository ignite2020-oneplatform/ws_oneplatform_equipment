package com.oneplatform.equipment.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Equipment", schema = "Equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipmentId;

    private String equipmentNumber;
}

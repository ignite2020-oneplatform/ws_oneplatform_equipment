package com.oneplatform.equipment.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Equipment", schema = "Equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipmentId;
    private String equipmentNumber;
    private String equipmentPrefix;
    private String equipmentType;
    private String businessUnit;
    private Integer length;
    private Integer width;
    private Integer height;
    private String equipmentCategoryCode;
    private String equipmentLoadedStatus;
    private String equipmentOperationalStatus;
    private String equipmentMaintenanceStatus;
    private String locationName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String gpsCurrentLocation;
    private String lastUpdatedGpsLocation;
    private BigDecimal latitude;
    private BigDecimal longitude;
}

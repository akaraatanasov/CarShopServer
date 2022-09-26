package com.nbu.carshop.domain;

import com.nbu.carshop.domain.enums.Make;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Make make;

    @NotNull
    private String model;

    @NotNull
    private String plateNumber;

    @NotNull
    private String manufactureYear;

    public Vehicle(Make make, String model, String year, String plateNumber, Long id) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.manufactureYear = year;
        this.plateNumber = plateNumber;
    }

    public Vehicle() {
        this.id = null;
        this.make = null;
        this.model = null;
        this.manufactureYear = null;
        this.plateNumber = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String year) {
        this.manufactureYear = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return plateNumber.equals(vehicle.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber);
    }
}

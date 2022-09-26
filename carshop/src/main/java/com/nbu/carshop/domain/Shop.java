package com.nbu.carshop.domain;

import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.domain.enums.RepairType;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Make make;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RepairType expertise;

    public Shop(String name, Make make, RepairType expertise, Long id) {
        this.id = id;
        this.name = name;
        this.make = make;
        this.expertise = expertise;
    }

    public Shop() {
        this.id = null;
        this.name = null;
        this.make = null;
        this.expertise = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public RepairType getExpertise() {
        return expertise;
    }

    public void setExpertise(RepairType expertise) {
        this.expertise = expertise;
    }
}

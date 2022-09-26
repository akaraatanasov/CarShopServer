package com.nbu.carshop.domain;

import com.nbu.carshop.domain.enums.RepairType;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "repair")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String date;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RepairType repairType;

    @OneToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @OneToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    private Double hoursWork;

    private BigDecimal pricePerHour;

    public Repair(RepairType repairType, Vehicle vehicle, String date, Double hoursWork, BigDecimal pricePerHour, Shop shop, Long id) {
        this.id = id;
        this.date = date;
        this.repairType = repairType;
        this.shop = shop;
        this.vehicle = vehicle;
        this.hoursWork = hoursWork;
        this.pricePerHour = pricePerHour;
    }

    public Repair() {
        this.id = null;
        this.date = null;
        this.repairType = null;
        this.shop = null;
        this.vehicle = null;
        this.hoursWork = null;
        this.pricePerHour = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String timeSlot) {
        this.date = timeSlot;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Double getHoursWork() {
        return hoursWork;
    }

    public void setHoursWork(Double hoursLabour) {
        this.hoursWork = hoursLabour;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal price) {
        this.pricePerHour = price;
    }
}

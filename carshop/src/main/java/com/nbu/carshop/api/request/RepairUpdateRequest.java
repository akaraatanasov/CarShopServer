package com.nbu.carshop.api.request;

import java.math.BigDecimal;

public class RepairUpdateRequest {
    private String date;
    private String repairType;
    private String vehiclePlateNumber;
    private Double hoursWork;
    private BigDecimal pricePerHour;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public Double getHoursWork() {
        return hoursWork;
    }

    public void setHoursWork(Double hoursWork) {
        this.hoursWork = hoursWork;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}

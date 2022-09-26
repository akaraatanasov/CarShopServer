package com.nbu.carshop.api.response;

import com.nbu.carshop.domain.enums.RepairType;

public class WorkerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private RepairType expertise;
    private Long shopId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RepairType getExpertise() {
        return expertise;
    }

    public void setExpertise(RepairType expertise) {
        this.expertise = expertise;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}

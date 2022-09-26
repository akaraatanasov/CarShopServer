package com.nbu.carshop.factory;

import com.nbu.carshop.domain.Repair;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.api.request.RepairRequest;
import com.nbu.carshop.api.response.RepairResponse;
import com.nbu.carshop.api.request.RepairUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RepairFactory {

    @Autowired
    private VehicleFactory vehicleFactory;

    public Repair fromRepairRequestAndShopAndVehicle(
            RepairRequest request, Shop shop, Vehicle vehicle) {
        return new Repair(
                RepairType.valueOf(request.getRepairType()),
                vehicle,
                request.getDate(),
                request.getHoursWork(),
                request.getPricePerHour(),
                shop,
                null);
    }

    public Repair fromRepairUpdateRequestAndShopAndVehicle(
            RepairUpdateRequest request, Shop shop, Vehicle vehicle) {
        return new Repair(
                RepairType.valueOf(request.getRepairType()),
                vehicle,
                request.getDate(),
                request.getHoursWork(),
                request.getPricePerHour(),
                shop,
                null);
    }

    public RepairResponse fromRepair(Repair repair) {
        RepairResponse response = new RepairResponse();
        response.setId(repair.getId());
        response.setRepairType(repair.getRepairType().name());
        response.setPricePerHour(repair.getPricePerHour());
        response.setShopId(repair.getShop().getId());
        response.setHoursWork(repair.getHoursWork());
        response.setVehicle(vehicleFactory.fromVehicle(repair.getVehicle()));
        response.setDate(repair.getDate());
        return response;
    }
}

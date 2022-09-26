package com.nbu.carshop.service;

import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleByPlateNumber(String plateNumber) {
        return vehicleRepository.findFirstByPlateNumber(plateNumber.trim().toUpperCase(Locale.ROOT));
    }
}

package com.nbu.carshop.repository;

import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.domain.enums.Make;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    List<Vehicle> findAll();

    Vehicle findFirstByPlateNumber(String plateNumber);

    List<Vehicle> findAllByMake(Make make);

    List<Vehicle> findAllByManufactureYear(String year);
}

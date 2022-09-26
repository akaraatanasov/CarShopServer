package com.nbu.carshop.controller;

import com.nbu.carshop.api.response.RepairResponse;
import com.nbu.carshop.domain.Repair;
import com.nbu.carshop.factory.RepairFactory;
import com.nbu.carshop.factory.VehicleFactory;
import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.api.request.VehicleRequest;
import com.nbu.carshop.api.response.VehicleResponse;
import com.nbu.carshop.service.ShopService;
import com.nbu.carshop.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleFactory vehicleFactory;

    @Autowired
    private RepairFactory repairFactory;

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<VehicleResponse> response = vehicleFactory.fromVehicleLists(vehicleService.getAllVehicles());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{plateNumber}")
    public ResponseEntity<VehicleResponse> getVehicleByPlateNumber(@PathVariable(value = "plateNumber") String plateNumber) {
        try {
            VehicleResponse response = vehicleFactory.fromVehicle(vehicleService.getVehicleByPlateNumber(plateNumber));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{plateNumber}/repairs")
    public ResponseEntity<List<RepairResponse>> getRepairsByPlateNumber(@PathVariable(value = "plateNumber") String plateNumber) {
        try {
            List<Repair> repairList = shopService.getRepairsByPlateNumber(plateNumber);
            List<RepairResponse> response = repairList
                    .stream()
                    .map(repair -> repairFactory.fromRepair(repair))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody VehicleRequest vehicleRequest) {
        try {
            Vehicle newVehicle = vehicleFactory.fromVehicleRequestResource(vehicleRequest);
            return new ResponseEntity<>(vehicleFactory.fromVehicle(vehicleService.createVehicle(newVehicle)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
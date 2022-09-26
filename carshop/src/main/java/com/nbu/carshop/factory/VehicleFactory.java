package com.nbu.carshop.factory;

import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.api.request.VehicleRequest;
import com.nbu.carshop.api.response.VehicleResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleFactory {

    public Vehicle fromVehicleRequestResource(VehicleRequest request) {
        return new Vehicle(
                Make.valueOf(request.getMake()), // this throws IllegalArgumentException
                request.getModel(),
                request.getYear(),
                request.getPlateNumber(),
                null);
    }

    public VehicleResponse fromVehicle(Vehicle vehicle) {
        VehicleResponse response = new VehicleResponse();
        response.setId(vehicle.getId());
        response.setMake(vehicle.getMake().name());
        response.setModel(vehicle.getModel());
        response.setPlateNumber(vehicle.getPlateNumber());
        response.setYear(vehicle.getManufactureYear());
        return response;
    }

    public List<VehicleResponse> fromVehicleLists(List<Vehicle> vehicleList) {
        return vehicleList.stream().map(this::fromVehicle).collect(Collectors.toList());
    }
}

package com.nbu.carshop.service;

import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateVehicleSuccess() {
        // Given
        Vehicle vehicle = new Vehicle(null, null, null, null, 1L);
        doReturn(vehicle).when(vehicleRepository).save(any(Vehicle.class));

        // When
        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);

        // Then
        assertNotNull(createdVehicle);
        assertEquals(1L, createdVehicle.getId());
    }

    @Test
    public void testGetVehicleByNumberPlateSuccess() {
        // Given
        Vehicle vehicle = new Vehicle(null, null, null, "CT0493PC", 1L);
        doReturn(vehicle).when(vehicleRepository).findFirstByPlateNumber(anyString());

        // When
        Vehicle foundVehicle = vehicleService.getVehicleByPlateNumber("CT0493PC");

        // Then
        assertNotNull(foundVehicle);
        assertEquals("CT0493PC", foundVehicle.getPlateNumber());
    }
}

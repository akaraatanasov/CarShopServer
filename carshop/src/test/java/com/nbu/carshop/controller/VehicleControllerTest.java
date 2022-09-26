package com.nbu.carshop.controller;

import com.nbu.carshop.api.request.VehicleRequest;
import com.nbu.carshop.api.response.VehicleResponse;
import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.factory.VehicleFactory;
import com.nbu.carshop.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

public class VehicleControllerTest {

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private VehicleFactory vehicleFactory;

    @Mock
    private VehicleService vehicleService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllVehiclesSuccess() {
        // Given
        Vehicle vehicle = new Vehicle(Make.RENAULT, "19", "1995", "CT0493PC", 1L);
        doReturn(Collections.singletonList(vehicle)).when(vehicleService).getAllVehicles();
        doCallRealMethod().when(vehicleFactory).fromVehicleLists(anyList());

        // When
        ResponseEntity<List<VehicleResponse>> response = vehicleController.getAllVehicles();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testAddVehicleSuccess() {
        // Given
        VehicleRequest request = new VehicleRequest();
        request.setMake("RENAULT");
        request.setModel("19");
        request.setYear("1995");
        request.setPlateNumber("CT0493PC");

        doCallRealMethod().when(vehicleFactory).fromVehicleRequestResource(any(VehicleRequest.class));

        // When
        ResponseEntity<VehicleResponse> response = vehicleController.addVehicle(request);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
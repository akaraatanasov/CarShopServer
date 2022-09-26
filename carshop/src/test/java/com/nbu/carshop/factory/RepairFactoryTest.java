package com.nbu.carshop.factory;

import com.nbu.carshop.domain.Repair;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.api.request.RepairRequest;
import com.nbu.carshop.api.response.RepairResponse;
import com.nbu.carshop.api.request.RepairUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;

public class RepairFactoryTest {

    @InjectMocks
    private RepairFactory repairFactory;

    @Mock
    private VehicleFactory vehicleFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFromCreateMaintenanceRequestResourceAndShopAndVehicleSuccess() {
        // Given
        Shop shop = new Shop(null, null, null, null);
        Vehicle vehicle = new Vehicle(null, null, null, "CT0493PC", 1L);
        RepairRequest request = new RepairRequest();
        request.setRepairType("ENGINE");
        request.setDate("12:34");
        request.setHoursWork(2d);
        request.setPricePerHour(BigDecimal.valueOf(260));
        request.setVehiclePlateNumber("CT0493PC");

        // When
        Repair repair = repairFactory.fromRepairRequestAndShopAndVehicle(request, shop, vehicle);

        // Then
        assertNotNull(repair);
        assertEquals(RepairType.ENGINE, repair.getRepairType());
        assertEquals("12:34", repair.getDate());
        assertEquals(2d, repair.getHoursWork());
        assertEquals(BigDecimal.valueOf(260), repair.getPricePerHour());
        assertEquals(1L, repair.getVehicle().getId());
    }

    @Test
    public void testFromUpdateMaintenanceRequestResourceAndShopAndVehicleSuccess() {
        // Given
        Shop shop = new Shop(null, null, null, null);
        Vehicle vehicle = new Vehicle(null, null, null, "CT0493PC", 1L);
        RepairUpdateRequest request = new RepairUpdateRequest();
        request.setRepairType("ENGINE");
        request.setDate("12:34");
        request.setHoursWork(2d);
        request.setPricePerHour(BigDecimal.valueOf(260));
        request.setVehiclePlateNumber("CT0493PC");

        // When
        Repair repair = repairFactory.fromRepairUpdateRequestAndShopAndVehicle(request, shop, vehicle);

        // Then
        assertNotNull(repair);
        assertEquals(RepairType.ENGINE, repair.getRepairType());
        assertEquals("12:34", repair.getDate());
        assertEquals(2d, repair.getHoursWork());
        assertEquals(BigDecimal.valueOf(260), repair.getPricePerHour());
        assertEquals(1L, repair.getVehicle().getId());
    }

    @Test
    public void testFromMaintenanceSuccess() {
        // Given
        Shop shop = new Shop(null, null, null, 3L);
        Vehicle vehicle = new Vehicle(Make.MERCEDES_BENZ, "E350", "2010", "CT0493PC", 1L);
        Repair repair = new Repair(RepairType.ENGINE, vehicle, "12:34", 2.0, BigDecimal.valueOf(260), shop, 2L);
        doCallRealMethod().when(vehicleFactory).fromVehicle(any(Vehicle.class));

        // When
        RepairResponse response = repairFactory.fromRepair(repair);

        // Then
        assertNotNull(response);
        assertEquals(2L, response.getId());
        assertEquals(RepairType.ENGINE.name(), response.getRepairType());
        assertEquals("12:34", response.getDate());
        assertEquals(2d, response.getHoursWork());
        assertEquals(BigDecimal.valueOf(260), response.getPricePerHour());
        assertEquals(1L, response.getVehicle().getId());
        assertEquals(3L, response.getShopId());
    }

}

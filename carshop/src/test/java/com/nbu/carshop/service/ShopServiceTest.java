package com.nbu.carshop.service;

import com.nbu.carshop.domain.Repair;
import com.nbu.carshop.domain.Worker;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.repository.RepairRepository;
import com.nbu.carshop.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ShopServiceTest {

    @InjectMocks
    private ShopService shopService;

    @Mock
    private ShopRepository shopRepository;

    @Mock
    private RepairRepository repairRepository;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private WorkerService workerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllShopsSuccess() {
        // Given
        Shop shop1 = new Shop(null, null, null, null);
        Shop shop2 = new Shop(null, null, null, null);
        doReturn(Arrays.asList(shop1, shop2)).when(shopRepository).findAll();

        // When
        List<Shop> allShops = shopService.getAllShops();

        // Then
        assertFalse(allShops.isEmpty());
        assertEquals(allShops.size(), 2);
    }

    @Test
    public void testGetShopByIdSuccess() throws Exception {
        // Given
        Shop shop = new Shop(null, null, null, 1L);
        doReturn(Optional.of(shop)).when(shopRepository).findById(1L);

        // When
        Shop shopById = shopService.getShopById(1L);

        // Then
        assertNotNull(shopById);
        assertEquals(shopById.getId(), 1L);
    }

    @Test
    public void testGetShopByIdAndShopDoesNotExists() throws Exception {
        // Given
        doReturn(Optional.empty()).when(shopRepository).findById(2L);

        // When
        Shop shopById = shopService.getShopById(2L);

        // Then
        assertNull(shopById);
    }

    @Test
    public void testCreateShopSuccess() {
        // Given
        Shop shop = new Shop("TestShop", null, null, null);
        doReturn(shop).when(shopRepository).save(any(Shop.class));

        // When
        Shop createdShop = shopService.createShop(shop);

        // Then
        assertEquals(createdShop.getName(), "TestShop");
    }

    @Test
    public void testCreateRepairSuccessWhenRepairIsGenericAndShopIsGeneric() throws Exception {
        // Given
        Shop shop = new Shop(null, null, RepairType.GENERIC, 1L);
        Vehicle vehicle = new Vehicle(Make.GENERIC, null, null, "CT0493PC", 2L);
        Repair repair = new Repair(RepairType.GENERIC, vehicle, "12:34", null, null, shop, null);
        Worker worker = new Worker(null, null, RepairType.GENERIC, null, null);

        doReturn(repair).when(repairRepository).save(any(Repair.class));
        doReturn(vehicle).when(vehicleService).getVehicleByPlateNumber(anyString());
        doReturn(Collections.singletonList(worker)).when(workerService).getAllWorkersByShopId(anyLong());
        doReturn(Optional.of(shop)).when(shopRepository).findById(1L);

        // When
        Repair createdRepair = shopService.createRepair(repair);

        // Then
        assertNotNull(createdRepair);
        assertEquals(createdRepair.getShop().getId(), shop.getId());
        assertEquals(createdRepair.getVehicle(), vehicle);
        verify(vehicleService, never()).createVehicle(any(Vehicle.class));
    }

    @Test
    public void testCreateRepairSuccessWhenRepairIsEngineAndShopIsGeneric() throws Exception {
        // Given
        Shop shop = new Shop(null, null, RepairType.GENERIC, 1L);
        Vehicle vehicle = new Vehicle(Make.GENERIC, null, null, "CT0493PC", 2L);
        Repair repair = new Repair(RepairType.ENGINE, vehicle, "12:34", null, null, shop, null);
        Worker worker = new Worker(null, null, RepairType.GENERIC, null, null);

        doReturn(repair).when(repairRepository).save(any(Repair.class));
        doReturn(vehicle).when(vehicleService).getVehicleByPlateNumber(anyString());
        doReturn(Collections.singletonList(worker)).when(workerService).getAllWorkersByShopId(anyLong());
        doReturn(Optional.of(shop)).when(shopRepository).findById(1L);

        // When
        Repair createdRepair = shopService.createRepair(repair);

        // Then
        assertNotNull(createdRepair);
        assertEquals(createdRepair.getShop().getId(), shop.getId());
        assertEquals(createdRepair.getVehicle(), vehicle);
        verify(vehicleService, never()).createVehicle(any(Vehicle.class));
    }

    @Test
    public void testCreateRepairSuccessWhenRepairIsEngineAndShopIsEngine() throws Exception {
        // Given
        Shop shop = new Shop(null, null, RepairType.ENGINE, 1L);
        Vehicle vehicle = new Vehicle(Make.GENERIC, null, null, "CT0493PC", 2L);
        Repair repair = new Repair(RepairType.ENGINE, vehicle, "12:34", null, null, shop, null);
        Worker worker = new Worker(null, null, RepairType.GENERIC, null, null);

        doReturn(repair).when(repairRepository).save(any(Repair.class));
        doReturn(vehicle).when(vehicleService).getVehicleByPlateNumber(anyString());
        doReturn(Collections.singletonList(worker)).when(workerService).getAllWorkersByShopId(anyLong());
        doReturn(Optional.of(shop)).when(shopRepository).findById(1L);

        // When
        Repair createdRepair = shopService.createRepair(repair);

        // Then
        assertNotNull(createdRepair);
        assertEquals(createdRepair.getShop().getId(), shop.getId());
        assertEquals(createdRepair.getVehicle(), vehicle);
        verify(vehicleService, never()).createVehicle(any(Vehicle.class));
    }

    @Test
    public void testCreateRepairSuccessWhenNewVehicle() throws Exception {
        // Given
        Shop shop = new Shop(null, null, RepairType.ENGINE, 1L);
        Vehicle vehicle = new Vehicle(Make.GENERIC, null, null, "CT0493PC", 2L);
        Repair repair = new Repair(RepairType.ENGINE, vehicle, "12:34", null, null, shop, null);
        Worker worker = new Worker(null, null, RepairType.GENERIC, null, null);

        doReturn(repair).when(repairRepository).save(any(Repair.class));
        doReturn(null).when(vehicleService).getVehicleByPlateNumber(anyString());
        doReturn(vehicle).when(vehicleService).createVehicle(any(Vehicle.class));
        doReturn(Collections.singletonList(worker)).when(workerService).getAllWorkersByShopId(anyLong());
        doReturn(Optional.of(shop)).when(shopRepository).findById(1L);

        // When
        Repair createdRepair = shopService.createRepair(repair);

        // Then
        assertNotNull(createdRepair);
        assertEquals(createdRepair.getShop().getId(), shop.getId());
        assertEquals(createdRepair.getVehicle(), vehicle);
        verify(vehicleService, times(1)).createVehicle(any(Vehicle.class));
        verify(vehicleService, times(1)).getVehicleByPlateNumber(anyString());
        verify(workerService, times(1)).getAllWorkersByShopId(anyLong());
    }

    @Test
    public void testCreateRepairExceptionWhenRepairIsEngineAndShopIsEngineButMechanicIsBrakes() throws Exception {
        // Given
        Shop shop = new Shop(null, null, RepairType.ENGINE, 1L);
        Vehicle vehicle = new Vehicle(Make.GENERIC, null, null, "CT0493PC", 2L);
        Repair repair = new Repair(RepairType.ENGINE, vehicle, "12:34", null, null, shop, null);
        Worker worker = new Worker(null, null, RepairType.BRAKES, null, null);

        doReturn(repair).when(repairRepository).save(any(Repair.class));
        doReturn(vehicle).when(vehicleService).getVehicleByPlateNumber(anyString());
        doReturn(Collections.singletonList(worker)).when(workerService).getAllWorkersByShopId(anyLong());
        doReturn(repair).when(repairRepository).save(any(Repair.class));

        // When
        Exception exception = assertThrows(Exception.class, () -> shopService.createRepair(repair));

        // Then
        assertNotNull(exception);
    }

}

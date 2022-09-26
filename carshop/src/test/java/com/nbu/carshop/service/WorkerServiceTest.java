package com.nbu.carshop.service;

import com.nbu.carshop.domain.Worker;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.repository.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

public class WorkerServiceTest {

    @InjectMocks
    private WorkerService workerService;

    @Mock
    private WorkerRepository workerRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMechanicWithGenericSpecialitySuccess() throws Exception {
        // Given
        Shop shop = new Shop(null, null, RepairType.GENERIC, null);
        Worker worker = new Worker(null, null, RepairType.GENERIC, shop, 1L);
        doReturn(worker).when(workerRepository).save(any(Worker.class));

        // When
        Worker createdWorker = workerService.createWorker(worker);

        // Then
        assertNotNull(createdWorker);
        assertEquals(1L, createdWorker.getId());
    }

    @Test
    public void testCreateMechanicWithEngineSpecialitySuccess() throws Exception {
        // Given
        Shop shop = new Shop(null, null, RepairType.GENERIC, null);
        Worker worker = new Worker(null, null, RepairType.ENGINE, shop, 1L);
        doReturn(worker).when(workerRepository).save(any(Worker.class));

        // When
        Worker createdWorker = workerService.createWorker(worker);

        // Then
        assertNotNull(createdWorker);
        assertEquals(1L, createdWorker.getId());
    }

    @Test
    public void testCreateMechanicWithEngineSpecialityAndShopSpecialitySuccess() throws Exception {
        // Given
        Shop shop = new Shop(null, null, RepairType.ENGINE, null);
        Worker worker = new Worker(null, null, RepairType.ENGINE, shop, 1L);
        doReturn(worker).when(workerRepository).save(any(Worker.class));

        // When
        Worker createdWorker = workerService.createWorker(worker);

        // Then
        assertNotNull(createdWorker);
        assertEquals(1L, createdWorker.getId());
    }

    @Test
    public void testCreateMechanicWithBrakesSpecialityException() throws Exception {
        // Given
        Shop shop = new Shop(null, null, RepairType.BRAKES, null);
        Worker worker = new Worker(null, null, RepairType.ENGINE, shop, 1L);
        doReturn(worker).when(workerRepository).save(any(Worker.class));

        // When
        Exception exception = assertThrows(Exception.class, () -> workerService.createWorker(worker));

        // Then
        assertNotNull(exception);
    }

    @Test
    public void testGetAllMechanicsSuccess() {
        // Given
        Worker worker1 = new Worker(null, null, null, null, null);
        Worker worker2 = new Worker(null, null, null, null, null);
        doReturn(Arrays.asList(worker1, worker2)).when(workerRepository).findAll();

        // When
        List<Worker> allWorkers = workerService.getAllWorkers();

        // Then
        assertNotNull(allWorkers);
        assertEquals(2, allWorkers.size());
    }

    @Test
    public void testGetAllMechanicsByShopIdSuccess() {
        // Given
        Shop shop = new Shop(null, null, null, 1L);
        Worker worker1 = new Worker(null, null, null, shop, null);
        Worker worker2 = new Worker(null, null, null, shop, null);
        doReturn(Arrays.asList(worker1, worker2)).when(workerRepository).findAllByShop_Id(anyLong());

        // When
        List<Worker> allWorkers = workerService.getAllWorkersByShopId(1L);

        // Then
        assertNotNull(allWorkers);
        assertEquals(2, allWorkers.size());
    }
}

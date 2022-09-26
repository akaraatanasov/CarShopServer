package com.nbu.carshop.controller;

import com.nbu.carshop.factory.WorkerFactory;
import com.nbu.carshop.domain.Worker;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.api.request.WorkerRequest;
import com.nbu.carshop.api.response.WorkerResponse;
import com.nbu.carshop.service.WorkerService;
import com.nbu.carshop.service.ShopService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

public class WorkerControllerTest {

    @InjectMocks
    private WorkerController workerController;

    @Mock
    private WorkerService workerService;

    @Mock
    private WorkerFactory workerFactory;

    @Mock
    private ShopService shopService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllWorkersSuccess() {
        // Given
        Worker worker = new Worker(null, null, RepairType.ENGINE, null, null);
        doReturn(Collections.singletonList(worker)).when(workerService).getAllWorkers();
        doCallRealMethod().when(workerFactory).fromWorkersList(anyList());

        // When
        ResponseEntity<List<WorkerResponse>> response = workerController.getAllWorkers();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testAddWorkerSuccess() throws Exception {
        // Given
        WorkerRequest request = new WorkerRequest();
        request.setExpertise("ENGINE");
        request.setFirstName("Ivan");
        request.setLastName("Ivanov");
        request.setShopId(1L);

        Shop shop = new Shop(null, null, null, 1L);
        doReturn(shop).when(shopService).getShopById(anyLong());
        doCallRealMethod().when(workerFactory).fromWorkerRequestAndShop(any(WorkerRequest.class), any(Shop.class));

        // When
        ResponseEntity<WorkerResponse> response = workerController.addWorker(request);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateWorkerServerError() throws Exception {
        // Given
        WorkerRequest request = new WorkerRequest();
        request.setExpertise("ENGINE");
        request.setFirstName("Ivan");
        request.setLastName("Ivanov");
        request.setShopId(1L);

        Shop shop = new Shop(null, null, null, 1L);
        doReturn(shop).when(shopService).getShopById(anyLong());
        doCallRealMethod().when(workerFactory).fromWorkerRequestAndShop(any(WorkerRequest.class), any(Shop.class));
        doThrow(new Exception()).when(workerService).createWorker(any(Worker.class));

        // When
        ResponseEntity<WorkerResponse> response = workerController.addWorker(request);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}

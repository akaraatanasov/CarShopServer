package com.nbu.carshop.controller;

import com.nbu.carshop.factory.WorkerFactory;
import com.nbu.carshop.domain.Worker;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.api.request.WorkerRequest;
import com.nbu.carshop.api.response.WorkerResponse;
import com.nbu.carshop.service.WorkerService;
import com.nbu.carshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private WorkerFactory workerFactory;

    @Autowired
    private ShopService shopService;

    @GetMapping
    public ResponseEntity<List<WorkerResponse>> getAllWorkers() {
        List<WorkerResponse> response = workerFactory.fromWorkersList(workerService.getAllWorkers());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{workerId}")
    public ResponseEntity<WorkerResponse> getWorkerByWorkerId(@PathVariable(value = "workerId") Long workerId) {
        try {
            WorkerResponse response = workerFactory.fromWorker(workerService.getWorkerById(workerId));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkerResponse> addWorker(@RequestBody WorkerRequest workerRequest) {
        try {
            Shop shop = shopService.getShopById(workerRequest.getShopId());
            Worker newWorker = workerFactory.fromWorkerRequestAndShop(workerRequest, shop);
            return new ResponseEntity<>(workerFactory.fromWorker(workerService.createWorker(newWorker)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

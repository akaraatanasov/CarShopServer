package com.nbu.carshop.factory;

import com.nbu.carshop.domain.Worker;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.api.request.WorkerRequest;
import com.nbu.carshop.api.response.WorkerResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkerFactory {

    public Worker fromWorkerRequestAndShop(WorkerRequest request, Shop shop) {
        return new Worker(
                request.getFirstName(),
                request.getLastName(),
                RepairType.valueOf(request.getExpertise()),
                shop,
                null);
    }

    public WorkerResponse fromWorker(Worker worker) {
        WorkerResponse response = new WorkerResponse();
        response.setId(worker.getId());
        response.setFirstName(worker.getFirstName());
        response.setLastName(worker.getLastName());
        response.setExpertise(worker.getExpertise());
        response.setShopId(worker.getShop().getId());
        return response;
    }

    public List<WorkerResponse> fromWorkersList(List<Worker> workerList) {
        return workerList.stream().map(this::fromWorker).collect(Collectors.toList());
    }
}

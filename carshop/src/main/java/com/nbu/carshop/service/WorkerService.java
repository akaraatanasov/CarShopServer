package com.nbu.carshop.service;

import com.nbu.carshop.domain.Worker;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public Worker createWorker(Worker worker) throws Exception {
        if (!isWorkerExpertForShop(worker)) {
            throw new Exception();
        }

        return workerRepository.save(worker);
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public List<Worker> getAllWorkersByShopId(Long shopId) {
        return workerRepository.findAllByShop_Id(shopId);
    }

    public Worker getWorkerById(Long workerId) throws Exception {
        Optional<Worker> worker = workerRepository.findById(workerId);
        if (!worker.isPresent()) {
            throw new Exception();
        }
        return worker.get();
    }

    private boolean isWorkerExpertForShop(Worker worker) {
        RepairType mechanicSpeciality = worker.getExpertise();
        RepairType shopSpeciality = worker.getShop().getExpertise();

        return mechanicSpeciality.equals(RepairType.GENERIC)
                || shopSpeciality.equals(RepairType.GENERIC)
                || mechanicSpeciality.equals(shopSpeciality);
    }
}

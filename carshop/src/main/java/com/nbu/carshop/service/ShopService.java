package com.nbu.carshop.service;

import com.nbu.carshop.domain.Repair;
import com.nbu.carshop.domain.Worker;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.repository.RepairRepository;
import com.nbu.carshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private WorkerService workerService;

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Shop getShopById(Long id) throws Exception {
        Optional<Shop> shop = shopRepository.findById(id);
        return shop.orElse(null);
    }

    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public Repair createRepair(Repair repair) throws Exception {
        if (!isRepairRequestValid(repair)) {
            throw new Exception();
        }

        Vehicle vehicle = vehicleService.getVehicleByPlateNumber(repair.getVehicle().getPlateNumber());
        Optional<Shop> shop = shopRepository.findById(repair.getShop().getId());

        if (vehicle == null) {
            vehicle = vehicleService.createVehicle(repair.getVehicle());
        }

        if (!shop.isPresent()) {
            throw new Exception();
        }

        repair.setShop(shop.get());
        repair.setVehicle(vehicle);
        return repairRepository.save(repair);
    }

    public List<Repair> getRepair(Long shopId, String plateNumber, String repairType) {
        List<Repair> filteredRepair = repairRepository.findAllByShop_Id(shopId);
        if (plateNumber != null && !plateNumber.trim().isEmpty() && plateNumber.length() == 8) {
            String lookUpPlateNumber = plateNumber.trim().toUpperCase(Locale.ROOT);
            filteredRepair = filteredRepair
                    .stream()
                    .filter(repair -> repair.getVehicle().getPlateNumber().equals(lookUpPlateNumber))
                    .collect(Collectors.toList());
        }
        if (repairType != null && !repairType.trim().isEmpty()) {
            RepairType lookUpRepairType = RepairType.valueOf(repairType.trim().toUpperCase(Locale.ROOT));
            filteredRepair = filteredRepair
                    .stream()
                    .filter(repair -> repair.getRepairType().equals(lookUpRepairType))
                    .collect(Collectors.toList());
        }
        return filteredRepair;
    }

    public List<Repair> getRepairsByPlateNumber(String plateNumber) {
        return repairRepository.findAllByVehiclePlateNumber(plateNumber);
    }

    public Repair getRepairById(Long id) {
        Optional<Repair> matchedRepair = repairRepository.findById(id);
        return matchedRepair.orElse(null);
    }

    public Repair updateRepair(Long repairId, Repair repair) throws Exception {
        if (!isRepairRequestValid(repair)) {
            throw new Exception();
        }
        if (repairRepository.findById(repairId).isPresent()) {
            repair.setId(repairId); // update existing repair with only non-null values ?
            return repairRepository.save(repair);
        }
        return null;
    }

    private boolean isRepairRequestValid(Repair repair) throws Exception {
        return repair.getDate() != null
                && !repair.getDate().trim().isEmpty()
                && isShopForMake(repair.getShop().getId(), repair.getVehicle().getMake())
                && areExpertWorkersAvailable(repair.getShop().getId(), repair.getRepairType());
    }

    private boolean isShopForMake(Long shopId, Make make) throws Exception {
        Shop shop = getShopById(shopId);
        return make.equals(Make.GENERIC) || shop.getMake().equals(Make.GENERIC) || shop.getMake().equals(make);
    }

    private boolean areExpertWorkersAvailable(Long shopId, RepairType repairType) {
        List<Worker> shopWorkers = workerService.getAllWorkersByShopId(shopId);
        return shopWorkers
                .stream()
                .map(Worker::getExpertise)
                .anyMatch(speciality -> speciality.equals(RepairType.GENERIC) || speciality.equals(repairType));
    }
}

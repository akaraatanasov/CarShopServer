package com.nbu.carshop;

import com.nbu.carshop.domain.Repair;
import com.nbu.carshop.domain.Worker;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.service.WorkerService;
import com.nbu.carshop.service.ShopService;
import com.nbu.carshop.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@SpringBootApplication
public class CarshopApplication {

    @Autowired
    private ShopService shopService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private VehicleService vehicleService;

    public static void main(String[] args) {
        SpringApplication.run(CarshopApplication.class, args);
    }

    @PostConstruct
    private void populateInitialData() throws Exception {
        Vehicle mercedes = new Vehicle(Make.MERCEDES_BENZ, "E350", "2019", "CA1234AP", null);
        Vehicle vw = new Vehicle(Make.VW, "GLE500", "2018", "CB4321BK", null);
        Vehicle lexus = new Vehicle(Make.LEXUS, "LS600", "2017", "CA0000BC", null);
        Vehicle bmw = new Vehicle(Make.BMW, "530", "2008", "BT7337PC", null);
        Vehicle renault = new Vehicle(Make.RENAULT, "19", "1995", "CT0493PC", null);
        vehicleService.createVehicle(mercedes);
        vehicleService.createVehicle(vw);
        vehicleService.createVehicle(lexus);
        vehicleService.createVehicle(bmw);
        vehicleService.createVehicle(renault);

        Shop mercedesShop = new Shop("Mercedes-Benz Shop", Make.MERCEDES_BENZ, RepairType.ENGINE, null);
        Shop allCarsShop = new Shop("All Cars Body Shop", Make.GENERIC, RepairType.BODY, null);
        Shop hoodShop = new Shop("Hood Shop", Make.GENERIC, RepairType.GENERIC, null);
        shopService.createShop(mercedesShop);
        shopService.createShop(allCarsShop);
        shopService.createShop(hoodShop);

        workerService.createWorker(new Worker("Ben", "Müller", RepairType.GENERIC, mercedesShop, null));
        workerService.createWorker(new Worker("Elias", "Schneider", RepairType.ENGINE, mercedesShop, null));
        workerService.createWorker(new Worker("Noah", "Weber", RepairType.ENGINE, mercedesShop, null));
        workerService.createWorker(new Worker("Peter", "Radev", RepairType.GENERIC, allCarsShop, null));
        workerService.createWorker(new Worker("Ivan", "Ivanov", RepairType.GENERIC, allCarsShop, null));
        workerService.createWorker(new Worker("Miroslav", "Georgiev", RepairType.GENERIC, allCarsShop, null));
        workerService.createWorker(new Worker("Alexander", "Dimitrov", RepairType.GENERIC, hoodShop, null));
        workerService.createWorker(new Worker("Stanislav", "Vasilev", RepairType.BRAKES, hoodShop, null));
        workerService.createWorker(new Worker("Vladimir", "Petrov", RepairType.SUSPENSION, hoodShop, null));

        shopService.createRepair(new Repair(RepairType.GENERIC, mercedes, "12:34", 2.0, BigDecimal.TEN, mercedesShop, null));
        shopService.createRepair(new Repair(RepairType.ENGINE, mercedes, "12:34", 5.0, BigDecimal.ONE, mercedesShop, null));
        shopService.createRepair(new Repair(RepairType.BODY, mercedes, "12:34", 0.5, BigDecimal.TEN, allCarsShop, null));
        shopService.createRepair(new Repair(RepairType.BRAKES, mercedes, "12:34", 10.0, BigDecimal.ONE, mercedesShop, null));
        shopService.createRepair(new Repair(RepairType.SUSPENSION, mercedes, "12:34", 7.0, BigDecimal.TEN, mercedesShop, null));
        shopService.createRepair(new Repair(RepairType.EXHAUST, lexus, "12:34", 1.0, BigDecimal.ONE, hoodShop, null));
        shopService.createRepair(new Repair(RepairType.PAINT, bmw, "12:34", 4.0, BigDecimal.TEN, hoodShop, null));
    }

}
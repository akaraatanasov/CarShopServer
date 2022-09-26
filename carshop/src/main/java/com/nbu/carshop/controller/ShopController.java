package com.nbu.carshop.controller;

import com.nbu.carshop.factory.RepairFactory;
import com.nbu.carshop.factory.ShopFactory;
import com.nbu.carshop.domain.Repair;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.Vehicle;
import com.nbu.carshop.api.request.RepairRequest;
import com.nbu.carshop.api.response.RepairResponse;
import com.nbu.carshop.api.request.ShopRequest;
import com.nbu.carshop.api.response.ShopResponse;
import com.nbu.carshop.api.request.RepairUpdateRequest;
import com.nbu.carshop.service.ShopService;
import com.nbu.carshop.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopFactory shopFactory;

    @Autowired
    private RepairFactory repairFactory;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<ShopResponse>> getAllShops() {
        List<ShopResponse> response = shopFactory.fromShopList(shopService.getAllShops());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShopResponse> createShop(@RequestBody ShopRequest shopRequest) {
        Shop newShop = shopFactory.fromShopRequestResource(shopRequest);
        ShopResponse response = shopFactory.fromShop(shopService.createShop(newShop));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{shopId}/repairs")
    public ResponseEntity<List<RepairResponse>> getRepairs(
            @PathVariable(value = "shopId") Long shopId,
            @RequestParam(value = "plateNumber", required = false) String plateNumber,
            @RequestParam(value = "repairType", required = false) String repairType) {
        try {
            List<Repair> repairList = shopService.getRepair(shopId, plateNumber, repairType);
            List<RepairResponse> response = repairList
                    .stream()
                    .map(repair -> repairFactory.fromRepair(repair))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{shopId}/repairs/{repairId}")
    public ResponseEntity<RepairResponse> getRepair(
            @PathVariable(value = "shopId") Long shopId,
            @PathVariable(value = "repairId") Long repairId) {
        try {
            Shop shop = shopService.getShopById(shopId);
            if (shop == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Repair repair = shopService.getRepairById(repairId);
            RepairResponse response = repairFactory.fromRepair(repair);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{shopId}/repairs",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepairResponse> addRepair(
            @PathVariable(value = "shopId") Long shopId,
            @RequestBody RepairRequest request) {
        try {
            Shop shop = shopService.getShopById(shopId);
            Vehicle vehicle = vehicleService.getVehicleByPlateNumber(request.getVehiclePlateNumber());
            Repair repair = shopService.createRepair(repairFactory.fromRepairRequestAndShopAndVehicle(request, shop, vehicle));
            RepairResponse responseResource = repairFactory.fromRepair(repair);
            return new ResponseEntity<>(responseResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{shopId}/repairs/{repairId}")
    public ResponseEntity<RepairResponse> updateRepair(
            @PathVariable(value = "shopId") Long shopId,
            @PathVariable(value = "repairId") Long repairId,
            @RequestBody RepairUpdateRequest request) {
        try {
            Shop shop = shopService.getShopById(shopId);
            Vehicle vehicle = vehicleService.getVehicleByPlateNumber(request.getVehiclePlateNumber());
            Repair repair = repairFactory.fromRepairUpdateRequestAndShopAndVehicle(request, shop, vehicle);
            Repair updatedRepair = shopService.updateRepair(repairId, repair);
            if (updatedRepair == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(repairFactory.fromRepair(updatedRepair), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

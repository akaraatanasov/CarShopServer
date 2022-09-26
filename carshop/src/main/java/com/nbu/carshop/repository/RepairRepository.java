package com.nbu.carshop.repository;

import com.nbu.carshop.domain.Repair;
import com.nbu.carshop.domain.enums.RepairType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {
    List<Repair> findAll();

    List<Repair> findAllByVehiclePlateNumberAndShop_IdAndRepairType(String plateNumber,
                                                                    Long shopId,
                                                                    String repairType);

    List<Repair> findAllByShop_IdAndRepairType(Long shopId, RepairType repairType);

    List<Repair> findAllByVehiclePlateNumberAndRepairType(String plateNumber, RepairType repairType);

    List<Repair> findAllByShop_Id(Long shopId);

    List<Repair> findAllByRepairType(RepairType repairType);

    List<Repair> findAllByVehiclePlateNumber(String plateNumber);
}

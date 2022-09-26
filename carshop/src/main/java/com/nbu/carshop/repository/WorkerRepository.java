package com.nbu.carshop.repository;

import com.nbu.carshop.domain.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Long> {
    List<Worker> findAll();

    List<Worker> findAllByShop_Id(Long id);
}

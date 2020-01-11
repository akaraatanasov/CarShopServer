package com.nbu.carshop.entity.Client;

import com.nbu.carshop.entity.Car.Car;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany
    private List<Car> carsList;

    public Client() {
    }

    public Client(long id, String name, List<Car> carsList) {
        this.id = id;
        this.name = name;
        this.carsList = carsList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCarsList() {
        return carsList;
    }

    public void setCarsList(List<Car> carsList) {
        this.carsList = carsList;
    }
}
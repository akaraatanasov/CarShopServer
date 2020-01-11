package com.nbu.carshop.entity.Car;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    private String plateNumber;

    @ManyToOne
    private CarMake make;

    private String model;

    private Short year;

    public Car() {
    }

    public Car(String plateNumber, CarMake make, String model, Short year) {
        this.plateNumber = plateNumber;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public CarMake getMake() {
        return make;
    }

    public void setMake(CarMake make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }
}

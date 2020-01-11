package com.nbu.carshop.entity.Appointment;

import com.nbu.carshop.entity.Car.Car;
import com.nbu.carshop.entity.Carshop.Carshop;
import com.nbu.carshop.entity.Client.Client;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Car car;

    private Date date;

    private Double price;

    public Appointment() {
    }

    public Appointment(long id, Client client, Car car, Double price) {
        this.id = id;
        this.client = client;
        this.car = car;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

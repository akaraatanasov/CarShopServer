package com.nbu.carshop.entity.Employee;

import com.nbu.carshop.entity.Carshop.Carshop;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = true)
    private String name;

    @ManyToOne
    private EmployeeQualification qualification;

    @ManyToOne
    private Carshop carshop;

    public Employee() {
    }

    public Employee(long id, String name, EmployeeQualification qualification, Carshop carshop) {
        this.id = id;
        this.name = name;
        this.qualification = qualification;
        this.carshop = carshop;
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

    public EmployeeQualification getQualification() {
        return qualification;
    }

    public void setQualification(EmployeeQualification qualification) {
        this.qualification = qualification;
    }

    public Carshop getCarshop() {
        return carshop;
    }

    public void setCarshop(Carshop carshop) {
        this.carshop = carshop;
    }
}

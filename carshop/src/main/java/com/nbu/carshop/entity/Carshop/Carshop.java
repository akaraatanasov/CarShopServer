package com.nbu.carshop.entity.Carshop;

import com.nbu.carshop.entity.Appointment.Appointment;
import com.nbu.carshop.entity.Car.CarMake;
import com.nbu.carshop.entity.Employee.Employee;
import com.nbu.carshop.entity.Employee.EmployeeQualification;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    private List<CarMake> makesList;

    @OneToMany
    private List<Employee> employeesList;

    @OneToMany
    private List<Appointment> appointmentsList;

    public Carshop() {
    }

    public Carshop(long id, String name, List<CarMake> makesList, List<Employee> employeesList, List<Appointment> appointmentsList) {
        this.id = id;
        this.name = name;
        this.makesList = makesList;
        this.employeesList = employeesList;
        this.appointmentsList = appointmentsList;
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

    public List<CarMake> getMakesList() {
        return makesList;
    }

    public void setMakesList(List<CarMake> makesList) {
        this.makesList = makesList;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public List<Appointment> getAppointmentsList() {
        return appointmentsList;
    }

    public void setAppointmentsList(List<Appointment> appointmentsList) {
        this.appointmentsList = appointmentsList;
    }

    public List<EmployeeQualification> getServicesList() {
        List<EmployeeQualification> servicesList = new ArrayList<>();
        employeesList.forEach(employee -> servicesList.add(employee.getQualification()));
        return servicesList;
    }

}

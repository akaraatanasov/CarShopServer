package com.nbu.carshop.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "credits", nullable = false)
    private int credits;
    @Column(name = "grades", nullable = true)
    private int grades;

    @ManyToOne
    @JoinColumn(name = "program")
    private Program program;

    public Course() {
        this.grades = 2;
        this.program = new Program(0);
    }

    public Course(long id, String name, int credits, long programId) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.grades = 2;
        this.program = new Program(programId);
    }

    public Course(long id, String name, int credits, int grades, long programId) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.grades = grades;
        this.program = new Program(programId);
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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getGrades() {
        return grades;
    }

    public void setGrades(int grades) {
        this.grades = grades;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", credits=" + credits + ", grades=" + grades + ", program=" + program + '}';
    }

}
package com.EMS.EmployeeMS.model;

import jakarta.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private int numberOfStaff;
    private String HeadOfDepartment;

    public Department(int id, String name, int numberOfStaff, String headOfDepartment) {
        Id = id;
        this.name = name;
        this.numberOfStaff = numberOfStaff;
        HeadOfDepartment = headOfDepartment;
    }

    public Department() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStaff() {
        return numberOfStaff;
    }

    public void setNumberOfStaff(int numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

    public String getHeadOfDepartment() {
        return HeadOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        HeadOfDepartment = headOfDepartment;
    }

    @Override
    public String toString() {
        return "Department{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", numberOfStaff=" + numberOfStaff +
                ", HeadOfDepartment='" + HeadOfDepartment + '\'' +
                '}';
    }
}

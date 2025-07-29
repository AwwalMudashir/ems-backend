package com.EMS.EmployeeMS.dto;

public class DepartmentDTO {
    private String name;
    private String HeadOfDepartment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadOfDepartment() {
        return HeadOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        HeadOfDepartment = headOfDepartment;
    }

    public DepartmentDTO(String name, String headOfDepartment) {
        this.name = name;
        HeadOfDepartment = headOfDepartment;
    }

    public DepartmentDTO() {
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "name='" + name + '\'' +
                ", HeadOfDepartment='" + HeadOfDepartment + '\'' +
                '}';
    }

}

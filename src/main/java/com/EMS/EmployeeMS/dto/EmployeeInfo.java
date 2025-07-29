package com.EMS.EmployeeMS.dto;

import com.EMS.EmployeeMS.model.Department;
import com.EMS.EmployeeMS.model.Employee;

public class EmployeeInfo {
    private Employee employee;
    private Department department;

    public EmployeeInfo() {
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "employee=" + employee +
                ", department=" + department +
                '}';
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeInfo(Employee employee, Department department) {
        this.employee = employee;
        this.department = department;
    }
}

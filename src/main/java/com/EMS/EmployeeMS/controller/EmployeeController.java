package com.EMS.EmployeeMS.controller;

import com.EMS.EmployeeMS.dto.EmployeeDTO;
import com.EMS.EmployeeMS.dto.EmployeeInfo;
import com.EMS.EmployeeMS.model.Employee;
import com.EMS.EmployeeMS.repository.EmployeeRepo;
import com.EMS.EmployeeMS.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepo employeeRepo;

    @PostMapping("newEmployee")
    public ResponseEntity<?> newEmployee(@RequestBody EmployeeDTO employeeDTO){
//        create a new employee
         return employeeService.newEmployee(employeeDTO);
    }

    @DeleteMapping("deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id){
//        delete an employee
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable int id){
//        find a particular employee by id
        return employeeService.findEmployee(id);
    }

//    made to explain monolithic
    @GetMapping("employee2/{id}")
    public ResponseEntity<EmployeeInfo> findEmployee2(@PathVariable int id){
        return employeeService.findEmployee2(id);
    }

    @GetMapping("employeesByDepartId/{id}")
    public ResponseEntity<?> employeesByDepartment(@PathVariable int id){
        return employeeService.findEmployeesByDepartment(id);
    }

    @PutMapping("updateEmployee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id,@RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployee(id,employeeDTO);
    }

    @GetMapping("allEmployees")
    public ResponseEntity<?> allEmployees(){
        return employeeService.getAllEmployees();
    }

}

package com.EMS.EmployeeMS.controller;

import com.EMS.EmployeeMS.dto.DepartmentDTO;
import com.EMS.EmployeeMS.model.Department;
import com.EMS.EmployeeMS.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("new")
    public Department addDepartment(@RequestBody DepartmentDTO departmentDTO){
//        Creating a new department
        return departmentService.createDepartment(departmentDTO);
    }

    @GetMapping("departments")
    public List<Department> allDepartments(){
//        Getting all the departments
        return departmentService.viewDepartments();
    }

    @GetMapping("department/{id}")
    public Department findDepartment(@PathVariable int id){
//        Get a department based on ID
        return departmentService.findDepartment(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id){
//        Delete a Department
        return departmentService.deleteDepartment(id);
    }

    @PutMapping("update/{id}")
    public Department updateDepartment(@RequestBody DepartmentDTO departmentDTO, @PathVariable int id){
//        Update a Department
        return departmentService.updateDepartment(departmentDTO,id);
    }
}

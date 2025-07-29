package com.EMS.EmployeeMS.service;

import com.EMS.EmployeeMS.dto.DepartmentDTO;
import com.EMS.EmployeeMS.model.Department;
import com.EMS.EmployeeMS.model.Employee;
import com.EMS.EmployeeMS.repository.DepartmentRepo;
import com.EMS.EmployeeMS.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    EmployeeService employeeService;
    public Department createDepartment(DepartmentDTO departmentDTO) {
        Department d = new Department();

        d.setName(departmentDTO.getName());
        d.setHeadOfDepartment(departmentDTO.getHeadOfDepartment());
        d.setNumberOfStaff(0);

        return departmentRepo.save(d);
    }

    public List<Department> viewDepartments() {
        return departmentRepo.findAll();
    }

    public Department findDepartment(int id) {
        return departmentRepo.findById(id).get();
    }

    public ResponseEntity<?> deleteDepartment(int id) {
        List<Employee> employees;
        if(departmentRepo.existsById(id)){

            if(employeeRepo.findAll() != null){
                employees = employeeRepo.findAll();

                for (Employee emp:employees){
                    if (emp.getDepartId() == id){
                        employeeRepo.deleteById(emp.getId());
                    }
                }
            }else{
                return new ResponseEntity<>("No employees currently",HttpStatus.NOT_FOUND);
            }

            departmentRepo.deleteById(id);


            return new ResponseEntity<>("Department Deleted Successfuly", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Such Department",HttpStatus.NOT_FOUND);
        }

    }


    public Department updateDepartment(DepartmentDTO departmentDTO, int id) {
        Department d1 = departmentRepo.findById(id).get();

        d1.setName(departmentDTO.getName());
        d1.setHeadOfDepartment(departmentDTO.getHeadOfDepartment());

        return departmentRepo.save(d1);
    }
}

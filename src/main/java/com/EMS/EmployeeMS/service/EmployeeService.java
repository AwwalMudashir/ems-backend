package com.EMS.EmployeeMS.service;

import com.EMS.EmployeeMS.dto.EmailDetails;
import com.EMS.EmployeeMS.dto.EmployeeDTO;
import com.EMS.EmployeeMS.dto.EmployeeInfo;
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
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    EmailService emailService;


    public Employee setEmployeeDetails(Employee employee,EmployeeDTO employeeDTO){
        double salary = 0;
        double tax = 0;
        double balance = 0;

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAge(employeeDTO.getAge());
        employee.setDepartId(employeeDTO.getDepartId());

        int yoe = employeeDTO.getYoe();

        if(yoe < 3){
            salary = 5000;
            tax = 0.1;
        } else if (yoe < 5) {
            salary = 10000;
            tax = 0.15;
        } else if (yoe < 8) {
            salary = 20000;
            tax = 0.2;
        } else if (yoe < 10) {
            salary = 35000;
            tax = 0.25;
        }else{
            salary = 50000;
            tax = 0.35;
        }

        balance = salary - (tax*salary);

        employee.setYoe(yoe);
        employee.setSalary(salary);
        employee.setTax(tax);
        employee.setBalance(balance);

        employee = employeeRepo.save(employee);

        Department department = departmentRepo.findById(employee.getDepartId()).get();

        department.setNumberOfStaff(department.getNumberOfStaff() + 1);

        departmentRepo.save(department);

        return employee;
    }


    public ResponseEntity<?> newEmployee(EmployeeDTO employeeDTO) {
        if (!departmentRepo.existsById(employeeDTO.getDepartId())){
         return new ResponseEntity<>("No such Department ID, Employee not created",HttpStatus.NOT_FOUND);
        }

        Employee employee = new Employee();

        employee = setEmployeeDetails(employee,employeeDTO);

        EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient(employee.getEmail());
        emailDetails.setSubject("You've Been Hired !!!");
        emailDetails.setMessageBody("Welcome "+employee.getFirstName()
                + " " + employee.getLastName() + " To AwwalDevs." +
                "\nYour Salary will be $" + employee.getSalary() +
                " but with a tax of $" + employee.getTax() + " you will be taking home $" +
                employee.getBalance() + " per month. \nWelcome to AwwalDevs 😁.");

        emailService.sendMail(emailDetails);

       return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }


    public String deleteEmployee(int id) {
        if(employeeRepo.existsById(id)){
            Employee employee = employeeRepo.findById(id).get();

            if(departmentRepo.existsById(employee.getDepartId())){
                Department department = departmentRepo.findById(employee.getDepartId()).get();

                department.setNumberOfStaff(department.getNumberOfStaff() - 1);
                departmentRepo.save(department);
                employeeRepo.deleteById(id);

                EmailDetails emailDetails = new EmailDetails();

                emailDetails.setRecipient(employee.getEmail());
                emailDetails.setSubject("You've Been Sacked !!!");
                emailDetails.setMessageBody("Commiserations "+employee.getFirstName()
                        + " " + employee.getLastName() + " you service at AwwalDevs have been terminated." +
                        "\nWe Thank you greatly for your service, We hope to see you excel later on in life.");

                emailService.sendMail(emailDetails);
                return "Employee Deleted Successfully !";
            }
        }

        return "No Employee with such ID exists !";
    }

    public ResponseEntity<?> findEmployee(int id) {
        if (employeeRepo.existsById(id)){
            return new ResponseEntity<>(employeeRepo.findById(id).get(),HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>("No such Employee",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> findEmployeesByDepartment(int id) {
        if (departmentRepo.existsById(id)){
            List<Employee> employees = employeeRepo.findByDepartId(id);
            if (employees == null){
                return new ResponseEntity<>("No employees in this department",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(employees,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>("No such Department",HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<?> updateEmployee(int id, EmployeeDTO employeeDTO) {
        if(employeeRepo.existsById(id)){
            Employee emp = employeeRepo.findById(id).get();

            emp = setEmployeeDetails(emp,employeeDTO);

            return new ResponseEntity<>("Updated",HttpStatus.FOUND);

        }

        return new ResponseEntity<>("No such Employee.. can't update",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getAllEmployees() {

        if(employeeRepo.findAll() != null){
            List<Employee> employees = employeeRepo.findAll();
            return new ResponseEntity<>(employees,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>("No employees currently",HttpStatus.NOT_FOUND);
        }
    }

//    This method was created to explain the use of the monolithic architecture going on
//    and the opening for the microservices
    public ResponseEntity<EmployeeInfo> findEmployee2(int id) {
        EmployeeInfo employeeInfo = new EmployeeInfo();

        Employee employee = employeeRepo.findById(id).get();
        Department department = departmentRepo.findById(employee.getDepartId()).get();

        employeeInfo.setEmployee(employee);
        employeeInfo.setDepartment(department);

        return new ResponseEntity<>(employeeInfo,HttpStatus.FOUND);
    }
}

package com.EMS.EmployeeMS.repository;

import com.EMS.EmployeeMS.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    @Override
    boolean existsById(Integer integer);

    List<Employee> findByDepartId(Integer integer);
}

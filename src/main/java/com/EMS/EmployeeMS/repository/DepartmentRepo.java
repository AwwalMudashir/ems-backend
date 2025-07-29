package com.EMS.EmployeeMS.repository;

import com.EMS.EmployeeMS.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {
    @Override
    boolean existsById(Integer integer);
}

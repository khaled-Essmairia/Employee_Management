package com.example.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.spring.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

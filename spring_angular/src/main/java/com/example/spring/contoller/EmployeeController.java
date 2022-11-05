package com.example.spring.contoller;

import java.util.List;
import java.util.Map;

import javax.management.relation.RoleInfoNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.Repository.EmployeeRepository;
import com.example.spring.exception.RessorceNotFoundException;
import com.example.spring.model.Employee;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRrepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRrepository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee){
		return employeeRrepository.save(employee);
	}

	@GetMapping ("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee = employeeRrepository.findById(id)
				.orElseThrow(()-> new RessorceNotFoundException("Employee not found with id :" + id));		
		 return ResponseEntity.ok(employee) ;	
	}
	
	@PostMapping("/employees/{id}")
	public ResponseEntity<Employee> updadteEmployee (@PathVariable Long id , @RequestBody Employee employeeDetails){
		Employee employee = employeeRrepository.findById(id)
				.orElseThrow(()-> new RessorceNotFoundException("Employe not exist with id :" + id));
	  
	   employee.setFirstName(employeeDetails.getFirstName());
	   employee.setLastname(employeeDetails.getLastname());
	   employee.setEmailId(employeeDetails.getEmailId());
	   
	   Employee updateEmployee = employeeRrepository.save(employee);
	   return ResponseEntity.ok(updateEmployee);}
	   
	   
	   @DeleteMapping("/employees/{id}")
		public void deleteEmployee(@PathVariable Long id){
		   Employee employee = employeeRrepository.findById(id).orElseThrow(()->new RessorceNotFoundException("Employee not exist with id :" + id));
		   
		   employeeRrepository.delete(employee);   
		   
	   }
	
}

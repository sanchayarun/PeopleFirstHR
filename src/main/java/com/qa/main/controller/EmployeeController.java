package com.qa.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.qa.main.domain.Employee;
import com.qa.main.service.EmployeeService;



@RestController
@CrossOrigin      // This prevents the COR error when you try and access the API through js, this is used for localhost only not for internet 
@RequestMapping("/employee")   // this is to add a prefix so that you can have more than one controller
public class EmployeeController {

	
	private EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}
	
	
	// POST REQUESTS - CREATE
	
	@PostMapping("/create")
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {    // The return type should be a "ResponseEntity<>" otherwise you will get a mismatch type error
	
		return new ResponseEntity<Employee>(service.create(employee), HttpStatus.CREATED); // ALWAYS PUT "new" IN FRONT OF "ResponseEntity<>"
	}
	
	// GET REQUESTS - READ
	
	@GetMapping ("/getAll")
	public ResponseEntity<List<Employee>> getAll() {
		return new ResponseEntity<List<Employee>>(service.getAll(), HttpStatus.OK);  // use HttpStatus.OK
	}
	
	
	@GetMapping("/getByID/{id}")  // (id) allows any id to be picked up 
	public ResponseEntity<Employee> getByID(@PathVariable long id) {    
		return new ResponseEntity<Employee>(service.getByID(id), HttpStatus.OK);  
	}
	
	// Derived Query
//	≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
	@GetMapping("/getByFirstName/{firstName}")
		public ResponseEntity<List<Employee>> getByFirstName(@PathVariable String firstName) {
		return new ResponseEntity<List<Employee>> (service.getByFirstName(firstName), HttpStatus.OK);
	}
//	≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
	
//	Custom Query
//	≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
	@GetMapping("/getByLastName/{lastName}")
	public ResponseEntity<List<Employee>> getByLastName(@PathVariable String lastName) {
	return new ResponseEntity<List<Employee>> (service.getByLastName(lastName), HttpStatus.OK);
}
//	≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈

	
	
	// PUT REQUEST - UPDATE
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> update(@PathVariable long id, @RequestBody Employee employee) {    
		
		return new ResponseEntity<Employee>(service.update(id, employee), HttpStatus.OK); // use HttpStatus.OK or HttpStatus.CREATED (if you are deleting an instance then replacing it with another)
	}
		
		
	// DELETE REQUEST - DELETE
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {   
		return new ResponseEntity<Boolean>(service.delete(id), HttpStatus.NO_CONTENT);  // use HttpStatus.NO_CONTENT
	}}

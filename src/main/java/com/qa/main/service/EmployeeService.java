package com.qa.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.main.domain.Employee;
import com.qa.main.exceptions.EmployeeNotFoundException;
import com.qa.main.repos.EmployeeRepo;

@Service
public class EmployeeService {
	
	private EmployeeRepo repo;

	public EmployeeService(EmployeeRepo repo) {
		this.repo = repo;
	}

	// POST REQUESTS - CREATE

	public Employee create(Employee employee) {
		return repo.saveAndFlush(employee); // saved and immediately flushed (pushed) to the DB
	}

	// GET REQUESTS - READ

	public List<Employee> getAll() {
		return repo.findAll();
	}


	// Custom exception
	
	public Employee getByID(long id) { 
		return repo.findById(id).orElseThrow(EmployeeNotFoundException :: new); 
										
	}

	// Derived Query
//		≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
	public List<Employee> getByFirstName(String firstName) {
		return repo.findEmployeeByFirstName(firstName);
	}
//		≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈

	// Custom Query
//		≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
	public List<Employee> getByLastName(String lastName) {
		return repo.getByEmployeeLastName(lastName);
	}
//		≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈

	// PUT REQUEST - UPDATE

	public Employee update(long id, Employee employee) { 
		
		// Gets the existing entry
		Employee existing = repo.findById(id).get();

		// Updates the existing entry, to match the incoming object
		existing.setFirstName(employee.getFirstName());
		existing.setLastName(employee.getLastName());
		existing.setEmail(employee.getEmail());
		existing.setMobile(employee.getMobile());

		// Save the updated entry back in the DB under the same ID
		return repo.saveAndFlush(existing);
	}

	// DELETE REQUEST - DELETE

	public boolean delete(long id) {

		repo.deleteById(id);

		return !repo.existsById(id); // returns true or false of whether the id exists, but we use "!" to show that
										// delete method returns true (i.e successful deletion)

	}
}

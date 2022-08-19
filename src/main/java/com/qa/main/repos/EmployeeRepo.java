package com.qa.main.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.main.domain.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	// SELECT * FROM employee WHERE first_name = {firstName}  -  The MySQL query sent under the hood
	List<Employee> findEmployeeByFirstName(String firstName);
	
	
	// This can be done using derived queries like above but as an example for custom queries this is shown
	@Query(value = "SELECT * FROM employee WHERE last_name = ?1", nativeQuery = true)
	List<Employee> getByEmployeeLastName(String lastName);
}

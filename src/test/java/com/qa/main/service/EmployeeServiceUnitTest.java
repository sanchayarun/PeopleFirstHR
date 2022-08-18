package com.qa.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.main.domain.Employee;
import com.qa.main.repos.EmployeeRepo;

	@SpringBootTest
	public class EmployeeServiceUnitTest {

		
		@Autowired
		private EmployeeService service;
		
		@MockBean
		private EmployeeRepo repo;
		
		
		@Test
		public void testCreate() {
			
			// Create An object for saving
			Employee entry = new Employee("Emily", "Bradfield", "emily.bradfield@outlook.com", "07312345679");
			
			// Create an object for the result
			Employee response = new Employee(2L,"Emily", "Bradfield", "emily.bradfield@outlook.com", "07312345679");
			
			
			Mockito.when(repo.saveAndFlush(entry)).thenReturn(response);
			
			assertEquals(response, service.create(entry));
		}


		@Test
		public void testGetAll() {
			// Create and object for saving
			List<Employee> response = new ArrayList<>();
			
			response.add(new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551"));

			Mockito.when(repo.findAll()).thenReturn(response);
			
			assertEquals(response, service.getAll());

		}
		
		
		
		@Test
		public void testGetByID() {
			
			Employee response = new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551");
			
			
			Mockito.when(repo.findById(1L)).thenReturn(Optional.of(response));  // change the return type to optional
			
			assertEquals(response, service.getByID(1L));

		}
		
		
		
		@Test
		public void testGetByFirstName() {
			
			List<Employee> response = new ArrayList<>();
			
			response.add(new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551"));
			
			Mockito.when(repo.findEmployeeByFirstName("Sanchayan")).thenReturn(response);  
			
			assertEquals(response, service.getByFirstName("Sanchayan"));

		}
		
		
		@Test
		public void testGetByLastName() {
			
			List<Employee> response = new ArrayList<>();
			
			response.add(new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551"));
			
			
			Mockito.when(repo.getByEmployeeLastName("Arudchelvam")).thenReturn(response);  
			
			assertEquals(response, service.getByLastName("Arudchelvam"));

		}
		
		@Test
		public void updateTest() throws Exception {
					
			Employee existing = new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551");
			Mockito.when(repo.findById(1L)).thenReturn(Optional.of(existing));
			
			Employee updateWithID = new Employee(1L, "Sanchay", "Arun", "sanchay.arun@outlook.com", "07716665551");
			Employee updateWithoutID = new Employee("Sanchay", "Arun", "sanchay.arun@outlook.com", "07716665551");
			Mockito.when(repo.saveAndFlush(updateWithID)).thenReturn(updateWithID);
			
			assertEquals(updateWithID, service.update(1L, updateWithoutID));
			
		}
		
		@Test
		public void deleteTest() throws Exception {
			
			Mockito.when(repo.existsById(1L)).thenReturn(false);
			
			assertEquals(true, service.delete(1L));
		
		}
		
	}


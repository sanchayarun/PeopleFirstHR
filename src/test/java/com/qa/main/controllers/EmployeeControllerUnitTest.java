package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Employee;
import com.qa.main.service.EmployeeService;

	
@WebMvcTest
public class EmployeeControllerUnitTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private EmployeeService service;
	
	@Test
	 public void createTest() throws Exception {
		
		// Create an object for posting
		Employee entry = new Employee("Emily", "Bradfield", "emily.bradfield@outlook.com", "07312345679");
		String entryAsJSON = mapper.writeValueAsString(entry);
		
		// Create an object to check the response
		Employee response = new Employee(2L, "Emily", "Bradfield", "emily.bradfield@outlook.com", "07312345679");
		String responseAsJSON = mapper.writeValueAsString(response);
		
		Mockito.when(service.create(entry)).thenReturn(response);
		
		mvc.perform(post("/employee/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(content().json(responseAsJSON));
//				.andExpect(status().isCreated());			
		
	}
	
	
	
	@Test
	public void getAllTest()throws Exception {
		
		// Create list to check the output of getALL()
		
		List<Employee> response = new ArrayList<>();
		
		// Add the single entry to the list
		response.add(new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551"));
		
		// Convert the list o JSON (The API responds in JSON)
		String responseAsJSON = mapper.writeValueAsString(response);
		
		Mockito.when(service.getAll()).thenReturn(response);
		
		
		mvc.perform(get("/employee/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(responseAsJSON));
	}
	
	
	@Test
	public void getByIdTest()throws Exception {
		
		Employee response = new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551");
		
		String responseAsJSON = mapper.writeValueAsString(response);
		
		Mockito.when(service.getByID(1L)).thenReturn(response);
		
		mvc.perform(get("/employee/getByID/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(responseAsJSON));
	}
	
	
	@Test
	public void getFirstNameTest()throws Exception {
		
	
		List<Employee> response = new ArrayList<>();
		
		response.add(new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551"));
		
		// Convert the list o JSON (The API responds in JSON)
		String responseAsJSON = mapper.writeValueAsString(response);
		
		Mockito.when(service.getByFirstName("Sanchayan")).thenReturn(response);
		
		mvc.perform(get("/employee/getByFirstName/Sanchayan")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(responseAsJSON));
	}
	
	
	@Test
	public void getLastNameTest()throws Exception {
		
		
		List<Employee> response = new ArrayList<>();
		
		response.add(new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551"));
		
		// Convert the list o JSON (The API responds in JSON)
		String responseAsJSON = mapper.writeValueAsString(response);
		
		Mockito.when(service.getByLastName("Arudchelvam")).thenReturn(response);
		
		mvc.perform(get("/employee/getByLastName/Arudchelvam")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(responseAsJSON));
	}
	
	
	
	@Test
	public void updateTest() throws Exception {
		
		Employee entry = new Employee("Sanchayan", "Arun", "sanchayan.arudchelvam@gmail.com", "07716665551");
		String entryAsJSON = mapper.writeValueAsString(entry);
		
		Employee response = new Employee(1L, "Sanchayan", "Arun", "sanchayan.arudchelvam@gmail.com", "07716665551");
		String responseAsJSON = mapper.writeValueAsString(response);
		
		Mockito.when(service.update(1L, entry)).thenReturn(response);
		
		mvc.perform(put("/employee/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(content().json(responseAsJSON));
//				.andExpect(status().isOk());	
	}
	

	@Test
	public void deleteTest() throws Exception {
		
		Mockito.when(service.delete(1L)).thenReturn(true);
		
		mvc.perform(delete("/employee/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	
}

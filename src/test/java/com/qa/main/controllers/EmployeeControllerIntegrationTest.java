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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Employee;

@SpringBootTest
@AutoConfigureMockMvc

@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")		
public class EmployeeControllerIntegrationTest {

	@Autowired					
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;	
	
	
	@Test
	public void createTest() throws Exception {
		
		
		Employee entry = new Employee("Sanchay", "Arun", "sanchay.arun@outlook.com", "07312345679");
		String entryAsJSON = mapper.writeValueAsString(entry);
		
		
		Employee response = new Employee(2L, "Sanchay", "Arun", "sanchay.arun@outlook.com", "07312345679");
		String responseAsJSON = mapper.writeValueAsString(response);
		
		
		mvc.perform(post("/employee/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(content().json(responseAsJSON));			
		
	}
	
	
	
	@Test
	public void getAllTest()throws Exception {
		

		
		List<Employee> response = new ArrayList<>();
		
		response.add(new Employee(1L, "Sanchayan", "Arudchelvam","sanchayan.arudchelvam@gmail.com", "07716665551" ));
		
		String responseAsJSON = mapper.writeValueAsString(response);
		
		mvc.perform(get("/employee/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(responseAsJSON));
	}
	
	
	@Test
	public void getByIdTest()throws Exception {
		
		
		Employee response = new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551");
		
		String responseAsJSON = mapper.writeValueAsString(response);
		
		mvc.perform(get("/employee/getByID/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(responseAsJSON));
	}
	
	
	@Test
	public void getFirstNameTest()throws Exception {
		
	
		List<Employee> response = new ArrayList<>();
		
		response.add(new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551"));
		
		String responseAsJSON = mapper.writeValueAsString(response);
		
		mvc.perform(get("/employee/getByFirstName/Sanchayan")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(responseAsJSON));
	}
	
	
	@Test
	public void getLastNameTest()throws Exception {
		
		
		List<Employee> response = new ArrayList<>();
		
		response.add(new Employee(1L, "Sanchayan", "Arudchelvam", "sanchayan.arudchelvam@gmail.com", "07716665551"));
		
		String responseAsJSON = mapper.writeValueAsString(response);
		
		mvc.perform(get("/employee/getByLastName/Arudchelvam")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(responseAsJSON));
	}
	
	
	
	@Test
	public void updateTest() throws Exception {
		
		Employee entry = new Employee(1L, "Sanchayan", "Arun", "sanchayan.arudchelvam@gmail.com", "07716665551");
		
		String entryAsJSON = mapper.writeValueAsString(entry);
		
		mvc.perform(put("/employee/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(content().json(entryAsJSON));	
	}
	

	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete("/employee/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
}

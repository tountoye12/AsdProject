package com.diallo.mockExam;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.diallo.mockExam.model.Employee;
import com.diallo.mockExam.repository.EmployeeRepository;
import com.diallo.mockExam.security.JwtService;
import com.diallo.mockExam.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class MockExamApplicationTests {


	private MockMvc mockMvc;

	private ObjectMapper objectMapper;


	private JwtService jwtService;

	@Autowired
	private EmployeeRepository employeeRepository;


	@Test
	public void getAllTesT(){
		List<Employee> employees = new ArrayList<>();
		var list = employeeRepository.findAll();

		assertEquals(employees.size(), list.size());
	}




//	@Test
//	@WithMockUser
//	public void getAllEmployeesTest() throws Exception {
//
//		String toking = generateMockJwtToken();
//		this.mockMvc.perform(get("/api/v1/employees/list").header(
//						"Authorization", "Bearer " + toking
//				).contentType(MediaType.APPLICATION_JSON))
//				.andDo(print())
//				.andExpect(status().isOk());
//		//ndExpect(jsonPath("$.length()").value(0));
//	}

	// Generate a JWT token using the JwtService
//	private String generateMockJwtToken() {
//		String mockUsername = "testUser";
//		return jwtService.generateToken(mockUsername);
//	}

//	@Test
//	void contextLoads() {
//	}


}

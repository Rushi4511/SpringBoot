package com.prod.springboot_production_ready;

import com.prod.springboot_production_ready.clients.EmployeeClient;
import com.prod.springboot_production_ready.clients.employeeClientImpl.EmployeeClientImpl;
import com.prod.springboot_production_ready.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.class)
class SpringbootProductionReadyApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(1)
	void getAllEmployees(){
		List<EmployeeDTO> savedEmployees =employeeClient.getAllEmployees();

		System.out.println(savedEmployees);

	}

	@Test
	@Order(3)
	void getEmployeeById(){
		EmployeeDTO savedEmployeeById =employeeClient.getEmployeeByID(2L);
		System.out.println(savedEmployeeById);
	}

	@Test
	@Order(2)
	void createNewEmployee(){
		EmployeeDTO createdEmployee =EmployeeDTO.builder()
				.id(1L)
				.age(22)
				.email("rushikarle45@gmail.com")
				.name("Rushi")
				.isActive(true)
				.build();
		System.out.println(createdEmployee);

	}



}

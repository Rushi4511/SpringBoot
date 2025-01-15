package com.SpringTesting.controllers;

import com.SpringTesting.TestContainerConfiguration;
import com.SpringTesting.dto.EmployeeDto;
import com.SpringTesting.entities.Employee;
import com.SpringTesting.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureWebTestClient(timeout = "100000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
class EmployeeControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee testEmployee;

    private EmployeeDto testEmployeeDto;

    @BeforeEach
    void setUp(){
        testEmployee = Employee.builder()

                .email("rushi@gmail.com")
                .name("Rushi")
                .salary(20L)
                .build();

        testEmployeeDto = EmployeeDto.builder()

                .email("rushi@gmail.com")
                .name("Rushi")
                .salary(20L)
                .build();
    }

    @Test
    void testGetEmployeeById_success(){
        Employee savedEmployee = employeeRepository.save(testEmployee);

        webTestClient.get()
                .uri("/employees/{id}",savedEmployee.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class);
                /*.isEqualTo(testEmployeeDto)
                .value(employeeDto -> {
                    assertThat(employeeDto.getEmail()).isEqualTo(savedEmployee.getEmail());
                    assertThat(employeeDto.getId()).isEqualTo(savedEmployee.getId());
                });*/
    }



}
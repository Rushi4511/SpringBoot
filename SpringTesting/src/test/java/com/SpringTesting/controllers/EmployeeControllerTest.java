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

        employeeRepository.deleteAll();
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

    @Test
    void testGetEmployeeById_failure(){

        webTestClient.get()
                .uri("/employees/1")
                .exchange()
                .expectStatus().isNotFound();

    }

    // CREATE NEW EMPLOYEE
    // 1. If Employee Exists

    @Test
    void testCreateNewEmployee_whenEmployeeAlreadyExists_thenThrowException(){

        Employee savedEmployee = employeeRepository.save(testEmployee);

        webTestClient.post()
                .uri("/employees")
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().is5xxServerError();

    }


    // 2. Create when Employee Doesn't exist

    @Test
    void testCreateNewEmployee_whenEmployeeDoesNotExists_thenCreateNewEmployee(){

        webTestClient.post()
                .uri("/employees")
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.email").isEqualTo(testEmployeeDto.getEmail())
                .jsonPath("$.name").isEqualTo(testEmployeeDto.getName());

    }


    // UPDATE EMPLOYEES

    // 1. When Employee Doesn't exist

    @Test
    void testUpdateEmployee_whenEmployeeDoesNotExists_thenThrowException(){

        webTestClient.put()
                .uri("/employees/999")
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().isNotFound();

    }


    // 2. When Attempting to change Email


    @Test
    void testUpdateEmployee_whenAttemptingToUpdateTheEmail_thenThrowException(){

        Employee savedEmployee = employeeRepository.save(testEmployee);

        testEmployeeDto.setName("Random Name");
        testEmployeeDto.setEmail("random@gmail.com");

        webTestClient.put()
                .uri("/employees/{id}",savedEmployee.getId())
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }


    // 3. Update when Employee is valid

    @Test
    void testUpdateEmployee_whenEmployeeIsValid_thenUpdateEmployee(){

        Employee savedEmployee = employeeRepository.save(testEmployee);

        testEmployeeDto.setName("Random Name");
        testEmployeeDto.setSalary(200L);

        webTestClient.put()
                .uri("/employees/{id}",savedEmployee.getId())
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .isEqualTo(testEmployeeDto);

    }


    //  DELETE employee

    //  1. When employee Doesn't exist

    @Test
    void testDeleteEmployee_whenEmployeeDoesNotExists_thenThrowException(){

        webTestClient.delete()
                .uri("/employees/1")
                .exchange()
                .expectStatus().isNotFound();

    }


    //  2. When employee  exist

    @Test
    void testDeleteEmployee_whenEmployeeExists_thenDeleteEmployee(){

        Employee savedEmployee = employeeRepository.save(testEmployee);

        webTestClient.delete()
                .uri("/employees/{id}",savedEmployee.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(Void.class);

        webTestClient.delete()
                .uri("/employees/1")
                .exchange()
                .expectStatus().isNotFound();


    }









}
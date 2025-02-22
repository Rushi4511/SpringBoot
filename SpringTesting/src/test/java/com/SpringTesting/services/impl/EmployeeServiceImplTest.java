package com.SpringTesting.services.impl;

import com.SpringTesting.TestContainerConfiguration;
import com.SpringTesting.dto.EmployeeDto;
import com.SpringTesting.entities.Employee;
import com.SpringTesting.exceptions.ResourceNotFoundException;
import com.SpringTesting.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestContainerConfiguration.class)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee mockEmployee;

    private EmployeeDto mockEmployeeDto;


//    @BeforeEach
//    void setUp(){
//        Long id = 1L;
//        mockEmployee = Employee.builder()
//                .id(id)
//                .email("rushi@gmail.com")
//                .name("Rushi")
//                .salary(200L)
//                .build();
//
//        mockEmployeeDto = modelMapper.map(mockEmployee,EmployeeDto.class);
//    }


    @Test
    void testGetEmployeeById_whenEmployeeIdisPresent_thenReturnEmployeeDto(){

        // assign

        Long id = mockEmployee.getId();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee)); //Stubbing


        // act
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);


        // assert
        assertThat(employeeDto.getId()).isEqualTo(id);
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());

        verify(employeeRepository).findById(id);
//        verify(employeeRepository).save(null); //This gives Error because this message is not used
        verify(employeeRepository).findById(1L);
    }

    @Test
    void testCreateNewEmployee_whenValidEmployee_thenCreateNewEmployee(){

        // assign
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);

        // act
        EmployeeDto employeeDto = employeeService.createNewEmployee(mockEmployeeDto);

        // assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployeeDto.getEmail());


    }


    // Employee is present or not and then Get Employee

    @Test
    void testGetEmployeeById_whenEmployeeIsNotPresent_thenThrowException(){
        // arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // act and assert
        assertThatThrownBy(()->employeeService.getEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee Not Found with Id:1");

    }


    // Create New Employee

    @Test
    void testCreateNewEmployee_whenAttemptingToCreateEmployeeWithExistingEmail_thenThrowException(){


        // arrange

        when(employeeRepository.findByEmail(mockEmployeeDto.getEmail())).thenReturn(List.of(mockEmployee));

        // act and assert

        assertThatThrownBy(()->employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email:"+mockEmployeeDto.getEmail());


        verify(employeeRepository).findByEmail(mockEmployeeDto.getEmail());
        verify(employeeRepository,never()).save(any());

    }

    // Update Employee When not Exist
    @Test
    void testUpdateEmployee_whenEmployee_whenEmployeeDoesNotExists_thenThrowException(){

        // arrange

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(()->employeeService.updateEmployee(mockEmployeeDto.getId(),mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee Not Found with Id:"+mockEmployeeDto.getId());

        verify(employeeRepository).findById(1L);
        verify(employeeRepository,never()).save(any());

    }

    @Test
    void testUpdateEmployee_whenAttemptingToUpdateEmail_thenThrowException(){
        // arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setName("Anuj");
        mockEmployeeDto.setEmail("Anuj@gmail.com");

        // act and assert
        assertThatThrownBy(()->employeeService.updateEmployee(mockEmployeeDto.getId(),mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The Email of the employee cannot be updated");


        verify(employeeRepository).findById(mockEmployeeDto.getId());
        verify(employeeRepository,never()).save(any());
    }


    @Test
    void testUpdate_whenValidEmployee_thenUpdateEmployee(){

        // arrange
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setName("Random Name");
        mockEmployeeDto.setSalary(199L);

        Employee newEmployee = modelMapper.map(mockEmployeeDto,Employee.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

        // act
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(mockEmployeeDto.getId(),mockEmployeeDto);

        assertThat(updatedEmployeeDto).isEqualTo(mockEmployeeDto);

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any());
    }


    // Delete Employee When Doesn't Exist

    @Test
    void testDeleteEmployee_whenEmployeeDoesNotExist_thenThrowException(){
        // arrange
        when(employeeRepository.existsById(1L)).thenReturn(false);

        // act
        assertThatThrownBy(()->employeeService.deleteEmployee(mockEmployeeDto.getId()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee Not Found with Id:"+1L);


        verify(employeeRepository,never()).deleteById(mockEmployeeDto.getId());
    }

    @Test
    void testDeleteEmployee_whenEmployeeIsValid_thenDeleteEmployee(){

        when(employeeRepository.existsById(1L)).thenReturn(true);

        assertThatCode(()->employeeService.deleteEmployee(mockEmployeeDto.getId()))
                .doesNotThrowAnyException();

        verify(employeeRepository).deleteById(1L);

    }






}
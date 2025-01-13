package com.SpringTesting.services.impl;

import com.SpringTesting.TestContainerConfiguration;
import com.SpringTesting.dto.EmployeeDto;
import com.SpringTesting.entities.Employee;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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


    @BeforeEach
    void setUp(){
        Long id = 1L;
        mockEmployee = Employee.builder()
                .id(id)
                .email("anuj@gmail.com")
                .name("Anuj")
                .salary(200L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee,EmployeeDto.class);
    }


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

}
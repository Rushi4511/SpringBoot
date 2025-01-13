//package com.SpringTesting.services.impl;
//
//import com.SpringTesting.TestContainerConfiguration;
//import com.SpringTesting.entities.Employee;
//import com.SpringTesting.repositories.EmployeeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.context.annotation.Import;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Import(TestContainerConfiguration.class)
//@ExtendWith(MockitoExtension.class)
//class EmployeeServiceImplTest {
//
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private EmployeeServiceImpl employeeService;
//
//
//
//
//    @Test
//    void testGetEmployeeById_whenEmployeeIdisPresent_thenReturnEmployeeDto(){
//
//        // assign
//
//        Employee mockEmployee = Employee.builder()
//                .id(id)
//                .email("anuj@gmail.com")
//                .name("Anuj")
//                .salary(200L)
//                .build();
//
//
//        employeeService.getEmployeeById(id).;
//
//    }
//
//}
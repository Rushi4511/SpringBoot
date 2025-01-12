package com.SpringTesting.services.impl;

import com.SpringTesting.dto.EmployeeDto;
import com.SpringTesting.entities.Employee;
import com.SpringTesting.exceptions.ResourceNotFoundException;
import com.SpringTesting.repositories.EmployeeRepository;
import com.SpringTesting.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto getEmployeeById(Long id) {

        log.info("Fetching employee with id: {}"+id);

        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Employee Not Found with Id:"+id
        ));


        log.info("Successfully deleted employee with id:{}"+id);
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {

        log.info("Creating employee with id:{} "+employeeDto.getId());

        List<Employee> existingEmployee = employeeRepository.findByEmail(employeeDto.getEmail());

        if(!existingEmployee.isEmpty()){
            log.error(" Employee already exists with email:{} "+employeeDto.getEmail());


            throw new RuntimeException("Employee already exists with email:"+employeeDto.getEmail());

        }

        Employee newEmployee = modelMapper.map(employeeDto,Employee.class);
        Employee savedEmployee = employeeRepository.save(newEmployee);


        log.info(" Successfully deleted employee with id:{} "+savedEmployee.getId());

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {

        log.info("Updating employee with id:{} "+id);
        Employee employee = employeeRepository.findById(id).orElseThrow(

                ()->new ResourceNotFoundException(
                        "Employee Not Found with Id:"+id
                )
        );

        if(!employee.getEmail().equals(employeeDto.getEmail())){

            log.error(" Attempted to update email for employee with Id:{} "+id);
            throw new RuntimeException("The Email of the employee cannot be updated");
        }

        employeeDto.setId(null);
        modelMapper.map(employeeDto,employee);
        Employee savedEmployee =employeeRepository.save(employee);

        log.error(" Successfully updated employee with Id:{} "+id);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id:{} "+id);
        boolean exists = employeeRepository.existsById(id);

        if(!exists) {
            log.error(" Employee Not Found with Id:{} "+id);
            throw new ResourceNotFoundException(
                    "Employee Not Found with Id:" + id
            );
        }

        employeeRepository.deleteById(id);

        log.info(" Successfully deleted employee with id:{} "+id);




    }
}

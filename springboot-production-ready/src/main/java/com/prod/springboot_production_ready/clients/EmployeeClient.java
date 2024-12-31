package com.prod.springboot_production_ready.clients;

import com.prod.springboot_production_ready.dto.EmployeeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;



public interface EmployeeClient {



    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeByID(Long employeeId);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}

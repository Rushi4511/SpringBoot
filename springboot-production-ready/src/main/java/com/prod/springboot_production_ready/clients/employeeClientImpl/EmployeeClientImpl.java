package com.prod.springboot_production_ready.clients.employeeClientImpl;

import com.prod.springboot_production_ready.advices.ApiError;
import com.prod.springboot_production_ready.advices.ApiResponse;
import com.prod.springboot_production_ready.clients.EmployeeClient;
import com.prod.springboot_production_ready.dto.EmployeeDTO;
import com.prod.springboot_production_ready.excpetions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient
                    .get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {}) ;

            return employeeDTOList.getData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeByID(Long employeeId) {
        try{

            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            return employeeResponse.getData();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{

            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse =restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        System.out.println(new String((res.getBody().readAllBytes())));
                        throw new ResourceNotFoundException("Could Not Create the Employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });

            return employeeDTOApiResponse.getBody().getData();


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

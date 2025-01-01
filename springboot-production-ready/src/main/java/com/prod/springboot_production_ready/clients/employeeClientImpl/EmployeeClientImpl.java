package com.prod.springboot_production_ready.clients.employeeClientImpl;

import com.prod.springboot_production_ready.advices.ApiError;
import com.prod.springboot_production_ready.advices.ApiResponse;
import com.prod.springboot_production_ready.clients.EmployeeClient;
import com.prod.springboot_production_ready.dto.EmployeeDTO;
import com.prod.springboot_production_ready.excpetions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);



    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Info executing");
        log.debug("I am Debuging before try");
        try {
            log.warn("This Code is executing");
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient
                    .get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
//                        System.out.println(new String((res.getBody().readAllBytes())));
                        log.error("is4xxClientError:Occurred in getAllEmployees{}",new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could Not Create the Employee");
                    })
                    .body(new ParameterizedTypeReference<>() {}) ;

            log.info("Success in Getting all Employees Data");
            return employeeDTOList.getData();


        } catch (Exception e) {
            log.error("I am in Errors");
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeByID(Long employeeId) {
        log.info("Info executing");
        log.debug("I am Debuging before try");
        try{
            log.warn("This Code is executing");

            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
//                        System.out.println(new String((res.getBody().readAllBytes())));
                        log.error("is4xxClientError:Occurred in getAllEmployeesBYId{}",new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could Not Create the Employee");
                    })
                    .body(new ParameterizedTypeReference<>() {});

            log.info("Success in Getting  Employees Data By ID");
            return employeeResponse.getData();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {

        log.info("Info executing");
        log.debug("I am Debuging before try");
        try{
            log.warn("This Code is executing");

            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse =restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error("is4xxClientError:Occurred in getAllEmployeesBYId : {}",new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could Not Create the Employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });

            log.info("Success in Creating Employee Data By ID");
            return employeeDTOApiResponse.getBody().getData();


        }catch (Exception e){

            throw new RuntimeException(e);
        }
    }
}

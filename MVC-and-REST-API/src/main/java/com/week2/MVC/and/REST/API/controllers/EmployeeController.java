package com.week2.MVC.and.REST.API.controllers;

import com.week2.MVC.and.REST.API.dto.EmployeeDTO;
import com.week2.MVC.and.REST.API.entities.EmployeeEntity;
import com.week2.MVC.and.REST.API.exceptions.ResourceNotFoundException;
import com.week2.MVC.and.REST.API.services.Employeeservice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path ="/employees")
public class EmployeeController {

//    @GetMapping(path ="/getSecretMessage")
//    public String getMySecretSuperMessage(){
//        return "Secret Message : asdfghjhj";
//    }
// 1.1
//    private final EmployeeRepository employeeRepository;

//    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    private final Employeeservice employeeservice;

    public EmployeeController(Employeeservice employeeservice) {
        this.employeeservice = employeeservice;
    }

    //1.1
//    @GetMapping(path ="/employees/{employeeId}")
//      @GetMapping(path ="/{employeeId}")
//      public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
//          return new EmployeeDTO(id,"Rushi","rushikarle45@gmail.com",21, LocalDate.of(2003,10,17),true);
//      }

// 1.2
//    @GetMapping(path ="/{employeeId}")
//    public EmployeeEntity getEmployeeById(@PathVariable(name="employeeId") Long id){
//        return employeeRepository.findById(id).orElse(null);
//    }
// 1.3
//    @GetMapping(path ="/{employeeId}")
//    public EmployeeEntity getEmployeeById(@PathVariable(name="employeeId") Long id){
//        return employeeservice.getEmployeeById(id);
//    }

    //1.4
    @GetMapping(path ="/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeservice.getEmployeeById(id);
//2.6.1        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
//                .orElse(ResponseEntity
//                .notFound()
//                .build());

        return employeeDTO.map(EmployeeDTO1->ResponseEntity.ok(EmployeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with ID"+id));
    }

    //2.7.1.1
//    @ExceptionHandler(NoSuchElementException.class)
//    public String handleEmployeeNotFound(NoSuchElementException exception){
//        return "EMployee Not FOUND EXCEPTION";
//    }

    //2.7.1.2
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception){
//        return new ResponseEntity<>("EMPLOYEE NOT FOUND",HttpStatus.NOT_FOUND);
//    }

//   1.1
//    @GetMapping(path = "employees")
//      @GetMapping(path ="")
//      public  String getAllEmployees(@RequestParam (required = false)Integer age,@RequestParam (required = false) String sortby){
//          return "Hi Age "+age +" and " + sortby;
//      }

    //1.2
//    @GetMapping(path ="")
//    public List<EmployeeEntity> getAllEmployees(@RequestParam (required = false)Integer age, @RequestParam (required = false) String sortby){
//        return employeeRepository.findAll();
//    }


    // 1.3
    @GetMapping(path ="")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam (required = false)Integer age, @RequestParam (required = false) String sortby){

        return ResponseEntity.ok(employeeservice.getAllEmployees());

    }

// 1.1
//      @PostMapping
//      public String createNewEmployee(){
//          return "Hello From POST";
//      }

//      @PostMapping
//      public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//          inputEmployee.setId(100L);
//          return inputEmployee;
//      }
//1.1
//1.2
//    @PostMapping
//    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
//        return employeeRepository.save(inputEmployee);
//    }
//
//    @PutMapping
//    public String updateEmployeeById(){
//        return "Hello From PUT";
//    }


    //1.3
    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){

        EmployeeDTO savedEmployee = employeeservice.createNewEmployee(inputEmployee);
        return new ResponseEntity<EmployeeDTO>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path ="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid  EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeservice.updateEmployeeById(employeeId,employeeDTO));
    }

    @DeleteMapping(path="/{employeeId}")
    public void deleteEmployeeById(Long employeeId){
        employeeservice.deleteEmployeeById(employeeId);
    }

    @PatchMapping(path ="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeservice.updatePartialEmployeeById(employeeId,updates);

        if(employeeDTO==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
    }

}

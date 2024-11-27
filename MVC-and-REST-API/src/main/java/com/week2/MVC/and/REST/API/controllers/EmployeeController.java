package com.week2.MVC.and.REST.API.controllers;

import com.week2.MVC.and.REST.API.dto.EmployeeDTO;
import com.week2.MVC.and.REST.API.entities.EmployeeEntity;
import com.week2.MVC.and.REST.API.services.Employeeservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
        return employeeservice.getEmployeeById(id);
    }

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
    public List<EmployeeDTO> getAllEmployees(@RequestParam (required = false)Integer age, @RequestParam (required = false) String sortby){
        return employeeservice.getAllEmployees();

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
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeservice.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hello From PUT";
    }

}

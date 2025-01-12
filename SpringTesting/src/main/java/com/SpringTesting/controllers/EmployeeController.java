package com.SpringTesting.controllers;


import com.SpringTesting.dto.EmployeeDto;
import com.SpringTesting.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){

        EmployeeDto employeeDto =employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employeeDto);

    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto createNewEmployeeDto = employeeService.createNewEmployee(employeeDto);

        return new ResponseEntity<>(createNewEmployeeDto,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto){

        EmployeeDto updateEmployeeDto =employeeService.updateEmployee(id,employeeDto);

        return ResponseEntity.ok(updateEmployeeDto);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();



    }






}

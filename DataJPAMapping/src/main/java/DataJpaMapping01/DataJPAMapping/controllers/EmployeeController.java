package DataJpaMapping01.DataJPAMapping.controllers;


import DataJpaMapping01.DataJPAMapping.entities.EmployeeEntity;
import DataJpaMapping01.DataJPAMapping.services.EmployeeServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
        return employeeServices.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeServices.createNewEmployee(employeeEntity);
    }
}

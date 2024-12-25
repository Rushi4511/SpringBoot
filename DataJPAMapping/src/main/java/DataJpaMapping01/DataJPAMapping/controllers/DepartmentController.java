package DataJpaMapping01.DataJPAMapping.controllers;

import DataJpaMapping01.DataJPAMapping.entities.DepartmentEntity;
import DataJpaMapping01.DataJPAMapping.services.DepartmentServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    @GetMapping("/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId){
        return departmentServices.getDepartmentById(departmentId);
    }

    @PostMapping
    public  DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity){
        return departmentServices.save(departmentEntity);
    }

    @PutMapping(path ="/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment( @PathVariable Long departmentId, @PathVariable Long employeeId){
        return departmentServices.assignManagerToDepartment(departmentId,employeeId);
    }

    @GetMapping(path = "/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfManager(@PathVariable Long employeeId){

        return departmentServices.getAssignedDepartmentOfManager(employeeId);
    }

    @PutMapping(path ="/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignedWorkerToDepartment( @PathVariable Long departmentId, @PathVariable Long employeeId){
        return departmentServices.assignedWorkerToDepartment(departmentId,employeeId);
    }



    @GetMapping(path="assignedDepartmentOfWorkers/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfWorkers(@PathVariable Long employeeId){
        return departmentServices.getAssignedDepartmentOfWorker(employeeId);
    }

    @PutMapping(path ="/{departmentId}/freelancer/{employeeId}")
    public DepartmentEntity assignedFreelancerToDepartment( @PathVariable Long departmentId, @PathVariable Long employeeId){
        return departmentServices.assignedFreelancerToDepartment(departmentId,employeeId);
    }



}

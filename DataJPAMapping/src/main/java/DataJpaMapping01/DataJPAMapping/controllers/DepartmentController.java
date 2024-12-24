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
}

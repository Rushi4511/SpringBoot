package DataJpaMapping01.DataJPAMapping.services;

import DataJpaMapping01.DataJPAMapping.entities.DepartmentEntity;
import DataJpaMapping01.DataJPAMapping.repositories.DepartmentRepository;
import DataJpaMapping01.DataJPAMapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServices {


    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public DepartmentServices(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }




    public DepartmentEntity getDepartmentById(Long departmentId) {

        return departmentRepository.findById(departmentId).orElse(null);

    }


    public DepartmentEntity save(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }
}

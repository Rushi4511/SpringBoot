package DataJpaMapping01.DataJPAMapping.services;

import DataJpaMapping01.DataJPAMapping.entities.EmployeeEntity;
import DataJpaMapping01.DataJPAMapping.repositories.DepartmentRepository;
import DataJpaMapping01.DataJPAMapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServices {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public EmployeeServices(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity getEmployeeById(Long employeeId) {

        return employeeRepository.findById(employeeId).orElse(null);

    }

    public EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }
}

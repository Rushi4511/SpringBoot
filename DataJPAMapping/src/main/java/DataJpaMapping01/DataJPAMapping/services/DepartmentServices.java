package DataJpaMapping01.DataJPAMapping.services;

import DataJpaMapping01.DataJPAMapping.entities.DepartmentEntity;
import DataJpaMapping01.DataJPAMapping.entities.EmployeeEntity;
import DataJpaMapping01.DataJPAMapping.repositories.DepartmentRepository;
import DataJpaMapping01.DataJPAMapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {

        Optional<DepartmentEntity> departmentEntity =departmentRepository.findById(departmentId);

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee ->{
                    department.setManager(employee);
                    return departmentRepository.save(department);
                })
                ).orElse(null);
    }

    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) {

//        Optional<DepartmentEntity> departmentEntity =departmentRepository.findById(departmentId);
//
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeId).build();

        return departmentRepository.findByManager(employeeEntity);

    }

    public DepartmentEntity assignedWorkerToDepartment(Long departmentId, Long employeeId) {

//        Optional<DepartmentEntity> departmentEntity =departmentRepository.findById(departmentId);
//
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

//        return departmentEntity.flatMap(department ->
//                employeeEntity.map(employee ->{
//
//                    employee.setWorkersDepartment(department);
//                    employeeRepository.save(employee);
//                    Set<EmployeeEntity> setOfEmp= department.getWorkers();
//                    setOfEmp.add(employee);
//                    return department;
//                })
//        ).orElse(null);

        Optional<DepartmentEntity> departmentEntity =departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        if(departmentEntity.isPresent() && employeeEntity.isPresent()){
            DepartmentEntity department = departmentEntity.get();
            EmployeeEntity employee = employeeEntity.get();
            employee.setWorkersDepartment(department);
            employeeRepository.save(employee);
            Set<EmployeeEntity> allWorkers = department.getWorkers();
            allWorkers.add(employee);
            return  department;
        }
        return null;
    }



    public DepartmentEntity getAssignedDepartmentOfWorker(Long employeeId) {

        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeId).build();
        return departmentRepository.findByWorkers(employeeEntity);
    }

    public DepartmentEntity assignedFreelancerToDepartment(Long departmentId, Long employeeId) {

        Optional<DepartmentEntity> department1 = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employee1=employeeRepository.findById(employeeId);

        return department1.flatMap(department -> employee1.map(employee ->{

            employee.getFreelancerDepartment().add(department);
            employeeRepository.save(employee);

            Set<EmployeeEntity> setOfFreelancers=department.getFreelancers();
            setOfFreelancers.add(employee);
            return department;
        } ) ).orElse(null);
    }
}

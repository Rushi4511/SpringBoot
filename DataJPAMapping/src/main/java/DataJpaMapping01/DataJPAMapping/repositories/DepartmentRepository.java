package DataJpaMapping01.DataJPAMapping.repositories;

import DataJpaMapping01.DataJPAMapping.entities.DepartmentEntity;
import DataJpaMapping01.DataJPAMapping.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    DepartmentEntity findByManager(EmployeeEntity employeeEntity);

    DepartmentEntity findByWorkers(EmployeeEntity employeeEntity);
}

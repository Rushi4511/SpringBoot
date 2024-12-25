package DataJpaMapping01.DataJPAMapping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "department_manager_id")
    private EmployeeEntity manager;

    @OneToMany(mappedBy = "workersDepartment")
    private Set<EmployeeEntity> workers;

    @ManyToMany(mappedBy = "freelancerDepartment")
    private Set<EmployeeEntity> freelancers;

    public DepartmentEntity() {
    }

    public DepartmentEntity(Long id, String title, EmployeeEntity manager, Set<EmployeeEntity> workers, Set<EmployeeEntity> freelancers) {
        this.id = id;
        this.title = title;
        this.manager = manager;
        this.workers = workers;
        this.freelancers = freelancers;
    }

    public Set<EmployeeEntity> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(Set<EmployeeEntity> freelancers) {
        this.freelancers = freelancers;
    }

    public Set<EmployeeEntity> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<EmployeeEntity> workers) {
        this.workers = workers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EmployeeEntity getManager() {
        return manager;
    }

    public void setManager(EmployeeEntity manager) {
        this.manager = manager;
    }
}

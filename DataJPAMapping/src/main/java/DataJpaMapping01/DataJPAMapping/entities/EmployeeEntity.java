package DataJpaMapping01.DataJPAMapping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.hibernate.engine.internal.Cascade;

import java.util.Objects;
import java.util.Set;

@Entity
@Builder
//@EqualsAndHashCode(of = "id")
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private DepartmentEntity managedDepartment;



    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="workers_department_id",referencedColumnName = "id")
    @JoinTable(name = "workers_department_mapping")
    @JsonIgnore
    private DepartmentEntity workersDepartment;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "freelancer_department_mapping",joinColumns = @JoinColumn(name = "employeeId"),inverseJoinColumns = @JoinColumn(name = "departmentId"))
    @JsonIgnore
    private Set<DepartmentEntity> freelancerDepartment;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public EmployeeEntity() {
    }

    public EmployeeEntity(Long id, String name, DepartmentEntity managedDepartment, DepartmentEntity workersDepartment, Set<DepartmentEntity> freelancerDepartment) {
        this.id = id;
        this.name = name;
        this.managedDepartment = managedDepartment;
        this.workersDepartment = workersDepartment;
        this.freelancerDepartment = freelancerDepartment;
    }

    public Set<DepartmentEntity> getFreelancerDepartment() {
        return freelancerDepartment;
    }

    public void setFreelancerDepartment(Set<DepartmentEntity> freelancerDepartment) {
        this.freelancerDepartment = freelancerDepartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentEntity getManagedDepartment() {
        return managedDepartment;
    }

    public void setManagedDepartment(DepartmentEntity managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    public DepartmentEntity getWorkersDepartment() {
        return workersDepartment;
    }

    public void setWorkersDepartment(DepartmentEntity workersDepartment) {
        this.workersDepartment = workersDepartment;
    }
}

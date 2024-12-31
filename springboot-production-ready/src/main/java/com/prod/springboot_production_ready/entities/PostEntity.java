package com.prod.springboot_production_ready.entities;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "posts")
@Audited
public class PostEntity extends AuditableEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


//  @NotAudited
    private String title;

    private String description;

    @PrePersist
    void beforeSave(){
        System.out.println("Before SAVING");
    }

    @PreUpdate
    void beforeUpdate(){
        System.out.println("Before UPDATE");
    }

    @PreRemove
    void beforeDelete(){
        System.out.println("Before DELETE");
    }
}

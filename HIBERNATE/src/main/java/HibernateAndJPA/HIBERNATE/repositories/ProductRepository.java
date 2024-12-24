package HibernateAndJPA.HIBERNATE.repositories;

import HibernateAndJPA.HIBERNATE.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}

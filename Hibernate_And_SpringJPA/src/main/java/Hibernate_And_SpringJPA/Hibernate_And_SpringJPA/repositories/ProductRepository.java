package Hibernate_And_SpringJPA.Hibernate_And_SpringJPA.repositories;

import Hibernate_And_SpringJPA.Hibernate_And_SpringJPA.entities.ProductEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime After);

    List<ProductEntity> findByQuantityAndPrice(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleContaining(String title);

//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);
    @Query("select e from ProductEntity e where e.title=?1 and e.price=?2")
    Optional<ProductEntity> findByTitleAndPrice(String title,BigDecimal price);

    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findByTitleContainingIgnoreCase(String title, PageRequest pageRequest);
}

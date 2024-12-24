package Hibernate_And_SpringJPA.Hibernate_And_SpringJPA;

import Hibernate_And_SpringJPA.Hibernate_And_SpringJPA.entities.ProductEntity;
import Hibernate_And_SpringJPA.Hibernate_And_SpringJPA.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class HibernateAndSpringJpaApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository(){

//		ProductEntity productEntity = new ProductEntity();
//		productEntity.setSku("nestle1234");
//		productEntity.setTitle("Rushi Nestle");
//		productEntity.setQuantity(12);
//		productEntity.setPrice(BigDecimal.valueOf(123.45));

		ProductEntity productEntity =ProductEntity.builder()
				.sku("nestle1234")
				.title("Rushi Nestle")
				.price(BigDecimal.valueOf(123.45))
				.quantity(12)
				.build();

		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);



	}

	@Test
	void getRepository(){

//		List<ProductEntity> entities = productRepository.findByTitle("Rushi Nestle");
//		System.out.println(entities);

//		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(LocalDateTime.of(2024,1,1,0,0,0));
//		System.out.println(entities);

//		List<ProductEntity> entities = productRepository.findByQuantityAndPrice(12,BigDecimal.valueOf(123.45));
//		System.out.println(entities);

//		List<ProductEntity> entities = productRepository.findByTitleContaining("Rushi Nestle");
//		System.out.println(entities);

//		Optional<ProductEntity> entities = productRepository.findByTitleAndPrice("Rushi Nestle",BigDecimal.valueOf(12345));
//		System.out.println(entities);


		Optional<ProductEntity> entities = productRepository.findByTitleAndPrice("Rushi Nestle",BigDecimal.valueOf(123.45));
		System.out.println(entities);


	}



}

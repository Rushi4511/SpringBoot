package HibernateAndJPA.HIBERNATE;

import HibernateAndJPA.HIBERNATE.entities.ProductEntity;
import HibernateAndJPA.HIBERNATE.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class HibernateApplicationTests {

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


}

package com.AOP.AspectOrientedProgramming;

import com.AOP.AspectOrientedProgramming.service.impl.ShipmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class AspectOrientedProgrammingApplicationTests {

	@Autowired
	private ShipmentServiceImpl shipmentService;

	@Test
	void contextLoads() {
	}

	@Test
	void aopTestOrderPackage(){
		String orderString =shipmentService.orderPackage(4L);
		log.info(orderString);
	}

	@Test
	void aopTestTrackPackage(){
		shipmentService.trackPackage(4L);
	}

}

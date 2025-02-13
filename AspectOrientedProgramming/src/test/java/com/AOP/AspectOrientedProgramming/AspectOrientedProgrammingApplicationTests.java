package com.AOP.AspectOrientedProgramming;

import com.AOP.AspectOrientedProgramming.service.impl.ShipmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AspectOrientedProgrammingApplicationTests {

	@Autowired
	private ShipmentServiceImpl shipmentService;

	@Test
	void contextLoads() {
	}

	@Test
	void aopTestOrderPackage(){
		shipmentService.orderPackage(4L);
	}

	@Test
	void aopTestTrackPackage(){
		shipmentService.trackPackage(4L);
	}

}

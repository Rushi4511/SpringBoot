package com.securityApp.SpringSecurity;

import com.securityApp.SpringSecurity.entities.User;
import com.securityApp.SpringSecurity.services.impl.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {


	@Autowired
	private JWTService jwtService;



	@Test
	void contextLoads() {

		User user = new User();
		user.setId(1L);
		user.setEmail("rushikarle45@gmail.com");
		user.setPassword("rushi123");



		String token =jwtService.generateToken(user);



		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);

	}





}

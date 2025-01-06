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

		User user = new User(1L,"rushikarle45@gmail.com","rushi123",null);
//		user.setId(1L);
//		user.setEmail("rushikarle45@gmail.com");
//		user.setPassword("rushi123");



		String token =jwtService.generateToken(user);



		System.out.println(token);

		Long id = jwtService.getUserIdFromToken("eyJhbGciOiJIUzUxMiJ9." +
		  "eyJzdWIiOiIxIiwiZW1haWwiOiJydUBnbWFpbC5jb20iLCJyb2xlcyI6WyJVU0VSIiwiQURNSU4iXSwiaWF0IjoxNzM2MTc4NjE2LCJleHAiOjE3MzYxNzg2NzZ9.rSZnj0TINd52BpLQ4m0ontSbTkchTvBMb_79B3PuRTXzxUn2WM9IXuOnt-sOCx2nr-SJjmFp6--NAZPyaxd5BA");
		System.out.println(id);

	}





}

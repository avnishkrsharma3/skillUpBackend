package com.cts.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.user.feign.AuthorisationClient;
import com.cts.user.model.User;
import com.cts.user.service.UserService;

@SpringBootTest
public class UserControllerTests {
	

	public UserController userController = new UserController();
	@Mock
	public AuthorisationClient authClient;
	@Mock
	public UserService userService;
	
	@Test
	void setUp() throws Exception {
		userController = new UserController();
	}
	
	@Test
	public void testHealthCheck() {
	    ResponseEntity<String> response = userController.healthCheck();
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Ok", response.getBody());
	}
	
//	@Test
//	public void testGetAllUsersByType() {
//	    String token = "token";
//	    String userType = "student";
////	    List<User> expectedUsers = Arrays.asList(
////	        new User(1, "user1", "student", "user1@gmail.com", "a", "b",  1),
////	        new User(1, "user1", "sweet", "user1@gmail.com", "a", "b",  1)
////	    );
//	    when(authClient.validate(token)).thenReturn(true);
//	    when(userService.getAllUsersByType(userType)).thenReturn(expectedUsers);
//	    ResponseEntity<List<User>> response = userController.getAllUsersByType(token, userType);
//	    assertEquals(HttpStatus.OK, response.getStatusCode());
//	    assertEquals(expectedUsers, response.getBody());
//	}
}
/*
	@Test
	public void testGetAllUsersByTypeWithInvalidToken() {
	    String token = "invalid-token";
	    String userType = "student";
	    when(authClient.validate(token)).thenReturn(false);
	    ResponseEntity<List<User>> response = userController.getAllUsersByType(token, userType);
	    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	    assertNull(response.getBody());
	}
   */
	


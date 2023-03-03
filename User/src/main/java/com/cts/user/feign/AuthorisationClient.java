package com.cts.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Proxy interface for authorization service
 */
@FeignClient(name = "authorization-service", url = "${AUTH_SERVICE:http://localhost:8081}")
public interface AuthorisationClient {

	/**
	 * Method for validating the token
	 * Author Avnish
	 * @param token
	 * @return This returns true if token is valid else returns false
	 */
	@GetMapping("/auth/user/validate")
	public boolean validate(@RequestHeader(name = "Authorization") String token);

	@GetMapping("/auth/user/{id}/{username}/{password}/{userType}")
	public String insertEntity(@PathVariable Integer id, 
			@PathVariable String username, @PathVariable String password,
			@PathVariable String userType);
	
	@DeleteMapping("/auth/user/{id}")
	public boolean deleteEntity(@PathVariable Integer id);
	
}
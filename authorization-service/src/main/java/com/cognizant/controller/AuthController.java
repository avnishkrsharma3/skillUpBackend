package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.LoginResponse;
import com.cognizant.exception.UserNameNumericException;
import com.cognizant.exception.UserNotFoundException;
import com.cognizant.model.MyUser;
import com.cognizant.model.UserCredentials;
import com.cognizant.service.UserDetailsServiceImpl;
import com.cognizant.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Class for Authorization Controller
 */
@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class AuthController {

//	static Logger log = (Logger) LoggerFactory.getLogger(AuthServiceApplication.class);
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
  /**
  * Author : AVNISH KUMAR
  * @param id
  * @param username
  * @param password
  * @return
  */
	@GetMapping("/user/{id}/{username}/{password}/{userType}")
	public ResponseEntity<String> insertEntity(@PathVariable Integer id, @PathVariable String username,
			@PathVariable String password, @PathVariable String userType ) {
		userDetailsService.registerUser(new MyUser(id, username, password, userType));
		return new ResponseEntity<>("Ok", HttpStatus.CREATED);
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Boolean> deleteEntity(@PathVariable Integer id){
		userDetailsService.deleteEntity(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}

	/**
	 * Method to validate the token
	 * Author : Avnish
	 * @param token1 This is the token send for authentication
	 * @return This returns true/false based on token validity
	 */

	@GetMapping("user/validate")
	public ResponseEntity<Boolean> validate(@RequestHeader(name = "Authorization") String token1) {
		String token = token1.substring(7);
		//`bearer_`
		try {
			UserDetails user = userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
			
			if (jwtUtil.validateToken(token, user)) {
				System.out.println("=================Inside Validate==================");
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Method to check whether login credentials are correct or not
	 * Author Avnish
	 * @param userCredentials user credentials contain user name and password
	 * @return This returns token on successful login else throws exception
	 */
	@PostMapping("/user/login")
	public ResponseEntity<LoginResponse> login(@RequestBody UserCredentials userCredentials) {
		if (userCredentials.getUserName() == null || userCredentials.getPassword() == null
				|| userCredentials.getUserName().trim().isEmpty() || userCredentials.getPassword().trim().isEmpty()) {
			log.debug("Login unsuccessful --> User name or password is empty");
			throw new UserNotFoundException("User name or password cannot be Null or Empty");
		}
		else if (jwtUtil.isNumeric(userCredentials.getUserName())) {
			log.debug("Login unsuccessful --> User name is numeric");
			throw new UserNameNumericException("User name is numeric");
		}
		else {
			try {
				UserDetails user = userDetailsService.loadUserByUsername(userCredentials.getUserName());
				if (user.getPassword().equals(userCredentials.getPassword())) {
					String token = jwtUtil.generateToken(user.getUsername());
					log.debug("Login successful");
					//return new ResponseEntity<String>(token, HttpStatus.ACCEPTED);
					MyUser myUser = userDetailsService.getMyUserTypeByUserNameAndPassword(user.getUsername(), user.getPassword());
					LoginResponse loginResponse  = new LoginResponse(token, myUser.getUserType(), myUser.getUserId() );
					return ResponseEntity.ok(loginResponse);
				}else {
					log.debug("Login unsuccessful --> Invalid password");
					throw new UserNotFoundException("Password is wrong");
				}
			} catch (Exception e) {
				log.debug("Login unsuccessful --> Invalid Credential");
				throw new UserNotFoundException("Invalid Credential");
			}
		}
	}
	

	
}

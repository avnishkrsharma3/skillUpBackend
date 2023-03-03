package com.cts.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.user.dto.RegisterResponse;
import com.cts.user.exception.UserAllReadyExistException;
import com.cts.user.feign.AuthorisationClient;
import com.cts.user.model.User;
import com.cts.user.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthorisationClient authClient;

	/**
	 * @author tanishka
	 * @return
	 */
	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}

	/**
	 * @author prashanth
	 * @param token
	 * @return
	 * 
	 *         To view the list of all users by type: student/instructor
	 */
	@GetMapping("/type/{userType}")
	public ResponseEntity<List<User>> getAllUsersByType(@RequestHeader(name = "Authorization") String token,
			@PathVariable("userType") String userType) {
		ResponseEntity<List<User>> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<List<User>>(userService.getAllUsersByType(userType), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/**
	 * @author tanishka
	 * @param token
	 * @param id
	 * @return
	 * 
	 *         To get user details by id
	 */

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@RequestHeader(name = "Authorization") String token,
			@PathVariable Integer id) {
		ResponseEntity<User> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/**
	 * @author tanishka
	 * @param token
	 * @param id
	 * @param user
	 * @return
	 * 
	 *         To update user details
	 */
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@Valid @RequestHeader(name = "Authorization") String token,
			@PathVariable Integer id, @RequestBody User user) {
		ResponseEntity<User> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/**
	 * @author tanishka
	 * @param token
	 * @param id
	 * @return
	 *         To delete user
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@RequestHeader(name = "Authorization") String token,
			@PathVariable Integer id) {
		ResponseEntity<User> entity = null;
		if (authClient.validate(token)) {
//			userService.deleteUser(id);
			authClient.deleteEntity(id);
			entity = new ResponseEntity<User>(userService.deleteUser(id), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/**
	 * Author Avnish
	 * 
	 * @param user
	 * @return
	 * @throws UserAllReadyExistException
	 */
	@PostMapping
	public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody User user) throws UserAllReadyExistException {
		User user1 = userService.registerUser(user);
		authClient.insertEntity(user1.getUserId(), user1.getEmail(), user1.getPassword(), user1.getUserType());
		RegisterResponse registerResponse = new RegisterResponse("success");
		return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
	}

}

package com.cognizant.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cognizant.model.MyUser;
import com.cognizant.repository.UserRepo;
import com.google.common.base.Optional;

import lombok.extern.slf4j.Slf4j;

/**
 * Avnish
 * Service implementation class for UserDetailsService
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	/**
	 * Overriding method to load the user details from database with user name
	 * 
	 * @param userName
	 * @return This returns the user name and password
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		MyUser user = userRepo.findByUserName(username);
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}
	
	public MyUser registerUser(MyUser user) {
		log.info("Inside register user ::::::::::::::::::");
		return userRepo.save(user);
	}
	public MyUser getMyUserTypeByUserNameAndPassword(String username, String Password) {
		 if(userRepo.getByNameAndPassword(username, Password)!=null) {
			 return userRepo.getByNameAndPassword(username, Password);
		 }
		 else {
			 return null;
		 }
	}

	public void deleteEntity(Integer id) {
		log.info(":::::::: deleting user from authorisation microservice :::::::");
		userRepo.deleteById(id);
	}

}

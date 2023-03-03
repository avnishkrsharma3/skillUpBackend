package com.cts.user.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.user.exception.UserAllReadyExistException;
import com.cts.user.exception.UserNotFoundException;
import com.cts.user.model.User;
import com.cts.user.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

//import com.cts.cohort.model.Cohort;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

    /**
     * Author Avnish
     * @throws UserAllReadyExistException 
     */
	@Override
	public User registerUser(User user) throws UserAllReadyExistException {
		log.info("Inside register service");
		Optional<User> findUserOptional = userRepo.findAll().stream()
				.filter((userAny) -> userAny.getEmail().equals(user.getEmail())).findFirst();
		if (findUserOptional.isPresent()) {
			log.info(findUserOptional.get().toString());
			log.info("user all ready exist");
			throw new UserAllReadyExistException("user with this email exist");
		} else
			return userRepo.save(user);
	}

		/**
	 * @author prashanth
	 * 
	 * To get all users details by type: student/instructor
	 */
	@Override
	public List<User> getAllUsersByType(String userType) {
		log.info("----Getting list of users by type----");
//		List<User> userList = userRepo.findAll();
		List<User> userList = userRepo.getAllUsersByType(userType);
			return userList;
	}
	/**
	 * @author tanishka
	 * 
	 * to delete user
	 */
	@Override
	public User deleteUser(Integer id) {
		User user = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("User not found for Delete"));
		userRepo.deleteById(id);
		return user;
	}

	/**
	 * @author tanishka
	 * To update user
	 */
	@Override
	public User updateUser(User user, Integer id) {
		// TODO Auto-generated method stub
		log.info("Inside update method");
		User existingUser = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for Update"));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setCohortId(user.getCohortId());
		existingUser.setUserType(user.getUserType());
		User updatedUser = userRepo.save(existingUser);
		return updatedUser;
	}


	/**
	 * @author tanishka
	 * 
	 * To get details of user by id
	 */
	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		log.info("----Getting user information----");
		User user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("No such user found"));
		return user;
	}

}

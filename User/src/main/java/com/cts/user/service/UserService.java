package com.cts.user.service;

import java.util.List;

import com.cts.user.exception.UserAllReadyExistException;
import com.cts.user.model.User;

public interface UserService {
	public List<User> getAllUsersByType(String userType);
	public User deleteUser(Integer id);
	public User updateUser(User user, Integer id);
	public User registerUser(User user) throws UserAllReadyExistException;
	public User getUserById(Integer id);
}

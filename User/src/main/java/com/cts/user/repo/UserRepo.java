package com.cts.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.user.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT * FROM user WHERE lower(userType) = lower(?1)", nativeQuery = true)
	public List<User> getAllUsersByType(String userType);

}

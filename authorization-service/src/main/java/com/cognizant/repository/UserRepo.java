package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.MyUser;

/**
 * Author Avnish
 * Repository class for storing, fetching and manipulating user data
 */
@Repository
public interface UserRepo extends JpaRepository<MyUser, Integer> {

	// Method to find a user details with user name
	public MyUser findByUserName(String name);
	@Query(value = "SELECT * FROM USER my WHERE my.name = ?1 AND my.password = ?2", nativeQuery = true)
	public MyUser getByNameAndPassword(String username, String password);
}

package com.cts.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.user.model.User;

import static org.hamcrest.Matchers.hasProperty;

import org.bouncycastle.est.ESTAuth;

import static org.hamcrest.MatcherAssert.assertThat;
/**
 * 
 * @author On team meeting Avnish + Tanishka;
 *
 */
@SpringBootTest
public class UserApplicationTests {
	private User user = new User();

	@Test
	void setUp() throws Exception {
		user = new User();
	}

	@Test
	public void testUserId() {
		user.setUserId(1);
		assertThat(user, hasProperty("userId"));
	}

	@Test
	public void testFirstName() {
		user.setFirstName("John");
		assertThat(user, hasProperty("firstName"));
	}

	@Test
	public void testLastName() {
		user.setLastName("Doe");
		assertThat(user, hasProperty("lastName"));
	}

	@Test
	public void testEmail() {
		user.setEmail("john.doe@example.com");
		assertThat(user, hasProperty("email"));
	}

	@Test
	public void testPassword() {
		user.setPassword("password");
		assertThat(user, hasProperty("password"));
	}

	@Test
	public void testUserType() {
		user.setUserType("admin");
		assertThat(user, hasProperty("userType"));
	}

	@Test
	public void testCohortId() {
		user.setCohortId(1);
		assertThat(user, hasProperty("cohortId"));
	}
}

package com.cts.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cts.user.model.Feedback;

class FeedbackTest {

	Feedback feedback=null;
	
	@BeforeEach
	void setUp() throws Exception {
		feedback = new Feedback();
	}

	@Test
	public void testFeedbackId() {
		assertThat(feedback, hasProperty("feedbackId"));
	}
	
	@Test
	public void testFeedbackText() {
		assertThat(feedback, hasProperty("feedbackText"));
	}
	
	@Test
	public void testStudentId() {
		assertThat(feedback, hasProperty("studentId"));
	}

}
package com.cts.user.service;

import java.util.List;
import java.util.Optional;

import com.cts.user.model.Feedback;

public interface FeedbackService {	
	public List<Feedback> getAllFeedbacks();
	Feedback addFeedback(Feedback feedback);
}

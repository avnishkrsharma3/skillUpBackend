package com.cts.user.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.user.exception.FeedbackNotFoundException;
import com.cts.user.model.Feedback;
import com.cts.user.repo.FeedbackRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	public FeedbackRepo feedbackRepo;

	/**
	 * @author Prashanth
	 */
	@Override
	public List<Feedback> getAllFeedbacks() {
		List<Feedback> feedbackList = feedbackRepo.findAll();
		if (feedbackList.size() == 0) {
			log.info("Exception occured");
			throw new FeedbackNotFoundException("No feedback found");
		} else {
			return feedbackRepo.findAll();
		}
	}

	/**
	 * @author Laxmikant
	 */
	@Override
	public Feedback addFeedback(Feedback feedback) {
		log.info("Inside addFeedback");
		return feedbackRepo.save(feedback);
	}

}

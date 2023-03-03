package com.cts.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.user.model.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
	

}

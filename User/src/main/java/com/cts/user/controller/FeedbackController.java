package com.cts.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.user.feign.AuthorisationClient;
import com.cts.user.model.Feedback;
import com.cts.user.service.FeedbackService;

@RestController
@CrossOrigin("*")
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private AuthorisationClient authClient;

	/*
	 * prashanth
	 */

	@GetMapping
	public ResponseEntity<List<Feedback>> getAllFeedbacks(@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<List<Feedback>> entity = null;

		if (authClient.validate(token)) {
			entity = new ResponseEntity<List<Feedback>>(feedbackService.getAllFeedbacks(), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/**
	 * @author Laxmikant
	 * @param token
	 * @param feedback
	 * @return
	 */

	@PostMapping
	public ResponseEntity<Feedback> addFeedback(@Valid @RequestHeader(name = "Authorization") String token,
			@RequestBody Feedback feedback) {
		ResponseEntity<Feedback> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<Feedback>(feedbackService.addFeedback(feedback), HttpStatus.CREATED);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

}

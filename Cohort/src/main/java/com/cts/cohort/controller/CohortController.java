package com.cts.cohort.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.cohort.exception.CohortAlreadyExistException;
import com.cts.cohort.feign.AuthorisationClient;
import com.cts.cohort.model.Cohort;
import com.cts.cohort.model.Courses;
import com.cts.cohort.service.CohortService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
public class CohortController {

	@Autowired
	private CohortService cohortService;

	@Autowired
	private AuthorisationClient authClient;

	/**
	 * @author Tanishka
	 * @param token
	 * @param cohort
	 * @return
	 * @throws CohortAlreadyExistException
	 */
	
	@PostMapping
	public ResponseEntity<Cohort> addCohort(@Valid @RequestHeader(name = "Authorization") String token,
			@RequestBody Cohort cohort) throws CohortAlreadyExistException {
		ResponseEntity<Cohort> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<Cohort>(cohortService.addCohort(cohort), HttpStatus.CREATED);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/**
	 * @author Anirudha
	 * @param token
	 * @return
	 */
	
	@GetMapping
	public ResponseEntity<List<Cohort>> getCohortList(@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<List<Cohort>> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<List<Cohort>>(cohortService.getCohort(), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/* Aniruddha */

	@GetMapping("/{id}")
	public ResponseEntity<Cohort> getCohortListById(@RequestHeader(name = "Authorization") String token,
			@PathVariable int id) {
		ResponseEntity<Cohort> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<Cohort>(cohortService.getCohortById(id), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/**
	 * Tanishka
	 * @param token
	 * @param cohort
	 * @param id
	 * @return
	 */
	
	@PutMapping("/{id}")
	public ResponseEntity<Cohort> updateCohort(@Valid @RequestHeader(name = "Authorization") String token,
			@RequestBody Cohort cohort, @PathVariable int id) {
		ResponseEntity<Cohort> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<Cohort>(cohortService.updateCohort(cohort, id), HttpStatus.CREATED);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;

	}
	
	/**
	 * @author Tanishka
	 * @param token
	 * @param id
	 * @return
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<Cohort> deleteCohort(@RequestHeader(name = "Authorization") String token,
			@PathVariable Integer id) {
		ResponseEntity<Cohort> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<Cohort>(cohortService.deleteCohort(id), HttpStatus.CREATED);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}
	
    /**
     * @author Avnish
     * @param token
     * @param id
     * @return
     */
	
	@GetMapping("/course/{id}")
	public ResponseEntity<Courses> getCourseByCohortId(@RequestHeader(name = "Authorization") String token,
			@PathVariable int id) {
		ResponseEntity<Courses> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<Courses>(cohortService.getCohortById(id).getCourse(), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	
}
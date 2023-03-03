package com.cts.cohort.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cohort.feign.AuthorisationClient;
import com.cts.cohort.model.Courses;
import com.cts.cohort.service.CourseService;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {

	@Autowired
	CourseService courseService;
	@Autowired
	AuthorisationClient authClient;

	/**
	 * @author Avnish
	 * @param token
	 * @return
	 */
	@GetMapping("/course")
	public ResponseEntity<List<Courses>> getCourseList(@RequestHeader(name = "Authorization") String token) {
		List<Courses> list = null;
		if (authClient.validate(token)) {
			list = courseService.getAllCourses();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Courses>>(HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * @author Avnish
	 * @param token
	 * @param course
	 * @return
	 */

	@PostMapping("/course")
	public ResponseEntity<Courses> addCourse(@RequestHeader(name = "Authorization") String token,
			@RequestBody Courses course) {

		if (authClient.validate(token)) {
			return new ResponseEntity<>(courseService.insert(course), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

}

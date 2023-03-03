package com.cts.cohort.service;

import java.util.List;

import com.cts.cohort.exception.CohortNotFoundException;
import com.cts.cohort.model.Courses;

public interface CourseService {

	List<Courses> getAllCourses() throws CohortNotFoundException;

	Courses insert(Courses course);

	Courses getById(Integer courseIdInteger);
}

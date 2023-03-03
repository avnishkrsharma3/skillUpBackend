package com.cts.cohort.service;

import java.util.List;

import com.cts.cohort.exception.CohortAlreadyExistException;
import com.cts.cohort.model.Cohort;
import com.cts.cohort.model.Courses;

public interface CohortService {
	public Cohort addCohort(Cohort cohort) throws CohortAlreadyExistException;
	
	public List<Cohort> getCohort();

	public Cohort getCohortById(int id);
	
	public Cohort updateCohort(Cohort cohort,Integer id);

	public Cohort deleteCohort(Integer id);

}
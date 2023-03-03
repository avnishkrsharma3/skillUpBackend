package com.cts.cohort.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cts.cohort.exception.CohortAlreadyExistException;
import com.cts.cohort.exception.CohortNotFoundException;
import com.cts.cohort.model.Cohort;
import com.cts.cohort.model.Courses;
import com.cts.cohort.repo.CohortRepo;
import com.cts.cohort.repo.CourseRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CohortServiceImpl implements CohortService {
	
	@Autowired
	public CohortRepo cohortRepo;

	@Autowired
	public CourseService courseService;

	/**
	 * @author Tanishka
	 */
	@Override
	public Cohort addCohort(Cohort cohort) throws CohortAlreadyExistException {
		log.info("inside add cohort");
		Optional<Cohort> existingCohort = cohortRepo.findById(cohort.getCohortId());
		log.info(existingCohort.isPresent()+"");
		if (existingCohort.isPresent()) {
			throw new CohortAlreadyExistException("Cohort already exist");
		}
		return cohortRepo.save(cohort);
	}

	/* Aniruddha */
	@Override
	public List<Cohort> getCohort() {
		List<Cohort> cohortList = cohortRepo.findAll();
		if (cohortList.size() == 0) {
			log.info("Exception occurred");
			throw new CohortNotFoundException("No cohort found");
		} else {
			return cohortList;
		}
	}

	/* Aniruddha */
	@Override
	public Cohort getCohortById(int id) {
		Cohort cohort = cohortRepo.findById(id).orElseThrow(() -> new CohortNotFoundException("No Cohort Found"));
		return cohort;
	}

	/**
	 * @author Tanishka
	 */
	@Override
	public Cohort updateCohort(Cohort cohort, Integer id) {
		Cohort originalData = cohortRepo.findById(id).orElseThrow(() -> new CohortNotFoundException("No Cohort Found"));
		originalData.setStartDate(cohort.getStartDate());
		originalData.setEndDate(cohort.getEndDate());
		originalData.setDuration(cohort.getDuration());
//		originalData.setCourseId(cohort.getCourseId());
		originalData.setInstructorId(cohort.getInstructorId());
		Integer courseIdInteger = cohort.getCourse().getCourseId();
		Courses courses = courseService.getById(courseIdInteger);
		cohortRepo.save(originalData);
		originalData.setCourse(courses);
		return originalData;
	}

	/**
	 * @author Tanishka
	 */
	@Override
	public Cohort deleteCohort(Integer id) {
		Cohort cohort = cohortRepo.findById(id)
				.orElseThrow(() -> new CohortNotFoundException("Cohort not found for Delete"));
		cohortRepo.deleteById(id);
		return cohort;
	}

}
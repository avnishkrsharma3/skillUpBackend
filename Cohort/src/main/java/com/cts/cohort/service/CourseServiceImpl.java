package com.cts.cohort.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.cohort.exception.CohortNotFoundException;
import com.cts.cohort.exception.CourseNotFoundException;
import com.cts.cohort.model.Courses;
import com.cts.cohort.repo.CourseRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepo courseRepo;

	/**
	 * @author Avnish
	 */
	@Override
	public List<Courses> getAllCourses() throws CohortNotFoundException {
			log.info("inside get all courses");
			List<Courses> list =  courseRepo.findAll();
			if(list.size() == 0) throw new CourseNotFoundException("No course found");
			return list;
	}

	/**
	 * @author Avnish
	 */
	@Override
	public Courses insert(Courses course) {
		
		return courseRepo.save(course);
	}
	@Override
    public Courses getById(Integer id) {
    	 Optional<Courses> courses =  courseRepo.findById(id);
    	 if(courses.isPresent()) return courses.get();
    	 else throw new CohortNotFoundException("no cohort Found");
    }
	
}

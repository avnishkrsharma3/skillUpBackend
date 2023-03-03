package com.cts.cohort.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.cohort.model.Courses;

@Repository
public interface CourseRepo extends JpaRepository<Courses, Integer> {


}

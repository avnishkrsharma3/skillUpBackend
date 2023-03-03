package com.cts.cohort.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.cohort.model.Cohort;

@Repository
public interface CohortRepo extends JpaRepository<Cohort, Integer> {

}

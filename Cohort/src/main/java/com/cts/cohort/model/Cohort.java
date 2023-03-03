package com.cts.cohort.model;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Model class for batches or cohort")
public class Cohort {
  // one cohort can have only one course (one to one)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "cohort Id as a primary key")
  private Integer cohortId;
  
  @ApiModelProperty(value = "Start date of the cohort")
  private Date startDate;
  
  @ApiModelProperty(value = "End date of the cohort")
  private Date endDate;
  
  @ApiModelProperty(value = "Duration of the cohort")
  private Integer duration;
  
  @ApiModelProperty(value = "Instructor id of instructor assigned to cohort")
  private Integer instructorId; 
  
  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name="course_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @NotNull(message = "Every cohort must have course")
  private Courses course ;  
  
  public String getCourseName() {
	  return this.course.getCourseName();
  }
  public Integer getCourseId() {
	  return this.course.getCourseId();
  }

}



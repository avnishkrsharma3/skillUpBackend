package com.cts.cohort.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity 
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Model class for Courses")
public class Courses { 
  // one course can have multiple cohorts (one to many)
  @Id
  @Column(name = "course_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "courseId as a primary key")
  private Integer courseId;
  @NotBlank(message = "Course name cannot be blank")
  @ApiModelProperty(value = "Name of the course")
  private String courseName;
  @ApiModelProperty(value = "Introduction of the course")
  private String courseBrief;
  @ApiModelProperty(value = "Duration of the course")
  private Integer duration;
  
  @OneToMany( cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="course") // lazy loading 
  @JsonManagedReference
  List<Cohort> cohortList = new ArrayList<>();  
}

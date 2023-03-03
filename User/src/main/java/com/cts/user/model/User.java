package com.cts.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Model class for User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "user id for user")
	private Integer userId;
	@NotNull(message = "Invalid First Name: First Name is NULL")
	@ApiModelProperty(value = "first Name for User")
	private String firstName;
	@ApiModelProperty(value = "last Name for User")
	private String lastName;
	@NotNull(message = "Invalid email: email is NULL")
	@ApiModelProperty(value = "email for User")
	private String email;
	@NotBlank(message = "password can not be blank")
	@ApiModelProperty(value = "Password for User")
	private String password;
	@ApiModelProperty(value = "userType of User")
	@NotBlank(message = "userType can not be blank")
	@Column(name = "USERTYPE")
	private String userType;
	@ApiModelProperty(value = "Cohort Id of User")
	private Integer cohortId;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user") // lazy loading
	@JsonManagedReference
	List<Feedback> feedbackList = new ArrayList<>();
}

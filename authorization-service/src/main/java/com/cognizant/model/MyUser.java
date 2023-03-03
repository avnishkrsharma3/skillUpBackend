package com.cognizant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class for storing user data in database
 * Author Avnish
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@ApiModel(description = "Model class for storing user credentials")
public class MyUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "user id")
	private Integer userId;

	@Column(name = "name")
	@NotBlank(message = "User name cannot be empty")
	@ApiModelProperty(value = "Email of user will be username")
	private String userName;

	@NotBlank(message = "Password cannot be blank")
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	@ApiModelProperty(value = "user password")
	private String password;
	@Column(name =  "usertype")
	@ApiModelProperty(value = "user role")
 	private String userType;

}

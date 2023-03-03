package com.cts.user.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Model class for feedback")
public class Feedback {

	@Id
	@ApiModelProperty(value = "feedback id ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedbackId;
	@NotBlank(message = "Feedback text cannot be blank")
	@ApiModelProperty(value = "feedback text ")
	private String feedbackText;
	//@ApiModelProperty(value = "Id of the Student")
	//private Integer studentId;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	public String getUserName() {
		return this.user.getFirstName();
	}
	public Integer getUserId() {
		return this.user.getUserId();
	}

}

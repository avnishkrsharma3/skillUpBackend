package com.cognizant.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Avnish
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponse {
	private String token;
	private String userType;
	private Integer idInteger;
}

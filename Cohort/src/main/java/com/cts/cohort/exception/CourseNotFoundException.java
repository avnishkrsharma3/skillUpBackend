package com.cts.cohort.exception;

/**
 * Class for handling UserNotFoundException
 */
public class CourseNotFoundException extends RuntimeException {

	public CourseNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * This method sets the custom error message
	 * 
	 * @param message
	 */
	private static final long serialVersionUID = 1L;

}
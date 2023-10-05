package com.example.ExampleAPI.student.exception;

import org.springframework.http.HttpStatus;

import com.example.ExampleAPI.exception.BaseException;

public class StudentException extends BaseException{
	private static final long serialVersionUID = 1L;

	protected StudentException(String code, HttpStatus status) {
		super("student." + code, status);	
	}
	 
	public static StudentException emptyStudent() {
	return new StudentException("findStudent.notFound", HttpStatus.BAD_REQUEST);
	}
}

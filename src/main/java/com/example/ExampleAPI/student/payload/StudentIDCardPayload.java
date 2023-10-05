package com.example.ExampleAPI.student.payload;

import com.example.ExampleAPI.student.model.Student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentIDCardPayload {
	private Student studentId;
	private String cardNumber;

}

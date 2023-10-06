package com.example.ExampleAPI.student.payload;

import com.example.ExampleAPI.student.model.Course;
import com.example.ExampleAPI.student.model.Student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnrolmentPayload {
	private Student studentId;
	private Course courseId;

}

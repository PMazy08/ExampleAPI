package com.example.ExampleAPI.student.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExampleAPI.student.json.CourseListJson;

import com.example.ExampleAPI.student.model.Course;

import com.example.ExampleAPI.student.payload.CoursePayload;

import com.example.ExampleAPI.student.service.CourseService;

@Service
public class CourseBusiness {
	
	@Autowired
	CourseService courseService;
	
	public CourseListJson getCourseByStudentId(long id) {
		return CourseListJson.packJson(courseService.findById(id));
	}
	
	public void saveCourse(CoursePayload ck) {
		Course course = new Course(
				ck.getName(),
				ck.getDepartment());
		courseService.save(course);
	}
	
	public void updateCourse(long id, CoursePayload payload) {
		Course courseData = courseService.findById(id);
		courseData.setDepartment(payload.getDepartment());
		courseData.setName(payload.getName());
		courseService.save(courseData);
	}
}

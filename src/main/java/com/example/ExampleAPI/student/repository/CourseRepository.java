package com.example.ExampleAPI.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ExampleAPI.student.model.Course;

public interface CourseRepository 
extends JpaRepository<Course, Long>{
	Course findById(long id);
	Course findByNameContaining(String name);
	Course findByDepartmentContaining(String name);

}

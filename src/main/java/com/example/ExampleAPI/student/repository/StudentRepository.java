package com.example.ExampleAPI.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ExampleAPI.student.model.Student;


public interface StudentRepository 
extends JpaRepository<Student, Long>{
	Student findById(long id);
	Student findByEmail(String emial);
	Optional<Student> findOptionalById(long id);
}

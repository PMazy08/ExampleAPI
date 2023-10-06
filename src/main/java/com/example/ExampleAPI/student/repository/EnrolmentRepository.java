package com.example.ExampleAPI.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.ExampleAPI.student.model.Enrolment;


public interface EnrolmentRepository extends JpaRepository<Enrolment, Long>{
	Enrolment findById(long id);
	Enrolment findByStudentId(long id);
	Enrolment findByCourseId(long id);
	Optional<Enrolment> findOptionalById(long id);
}

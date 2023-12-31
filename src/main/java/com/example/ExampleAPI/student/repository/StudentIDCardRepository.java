package com.example.ExampleAPI.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.ExampleAPI.student.model.StudentIDCard;

public interface StudentIDCardRepository 
extends JpaRepository<StudentIDCard, Long>{
	StudentIDCard findById(long id);
	StudentIDCard findByStudentId(long id);
	StudentIDCard findByCardNumberContaining(String number);
	Optional<StudentIDCard> findOptionalByStudentId(long id);
}

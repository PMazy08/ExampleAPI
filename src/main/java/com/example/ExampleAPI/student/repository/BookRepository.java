package com.example.ExampleAPI.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ExampleAPI.student.model.Book;


public interface BookRepository 
extends JpaRepository<Book, Long>{
	Book findById(long id);
	Book findByStudentId(long id);
	Book findByBookNameContaining(String bookName);
	Optional<Book> findOptionalById(long id);

}

package com.example.ExampleAPI.student.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExampleAPI.student.json.BookListJson;
import com.example.ExampleAPI.student.service.BookService;

@Service
public class BookBusiness {
	@Autowired
	BookService bookService;
	
	public BookListJson getBookByStudentId(long id) {
		return BookListJson.packJson(bookService.findByStudentId(id));
	}
}

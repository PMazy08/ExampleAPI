package com.example.ExampleAPI.student.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExampleAPI.student.json.BookListJson;
import com.example.ExampleAPI.student.model.Book;

import com.example.ExampleAPI.student.payload.BookPayload;

import com.example.ExampleAPI.student.service.BookService;

@Service
public class BookBusiness {
	@Autowired
	BookService bookService;
	
	public BookListJson getBookByStudentId(long id) {
		return BookListJson.packJson(bookService.findByStudentId(id));
	}
	
	public void saveBook(BookPayload bk) {
		Book book = new Book(
				bk.getStudentId(),
				bk.getBookName());
		bookService.save(book);
	}
	
	public void updateBook(long id, BookPayload payload) {
		Book bookData = bookService.findById(id);
		bookData.setBookName(payload.getBookName());;
		bookService.save(bookData);
	}

}

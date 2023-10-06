package com.example.ExampleAPI.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExampleAPI.exception.BaseException;
import com.example.ExampleAPI.student.business.BookBusiness;
import com.example.ExampleAPI.student.business.CourseBusiness;
import com.example.ExampleAPI.student.business.EnrolmentBusiness;
import com.example.ExampleAPI.student.business.StudentBusiness;
import com.example.ExampleAPI.student.business.StudentIDCardBusiness;
import com.example.ExampleAPI.student.exception.StudentException;
import com.example.ExampleAPI.student.json.BookListJson;
import com.example.ExampleAPI.student.json.CourseListJson;
import com.example.ExampleAPI.student.json.EnrolmentListJson;
import com.example.ExampleAPI.student.json.StudentIDCardListJson;
import com.example.ExampleAPI.student.json.StudentListJson;
import com.example.ExampleAPI.student.model.Book;
import com.example.ExampleAPI.student.model.Course;
import com.example.ExampleAPI.student.model.Enrolment;
import com.example.ExampleAPI.student.model.Student;
import com.example.ExampleAPI.student.model.StudentIDCard;
import com.example.ExampleAPI.student.payload.BookPayload;
import com.example.ExampleAPI.student.payload.CoursePayload;
import com.example.ExampleAPI.student.payload.EnrolmentPayload;
import com.example.ExampleAPI.student.payload.StudentIDCardPayload;
import com.example.ExampleAPI.student.payload.StudentPayload;
import com.example.ExampleAPI.student.service.BookService;
import com.example.ExampleAPI.student.service.CourseService;
import com.example.ExampleAPI.student.service.EnrolmentService;
import com.example.ExampleAPI.student.service.StudentIDCardService;
import com.example.ExampleAPI.student.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	StudentService studentservice;
	@Autowired
	StudentIDCardService studentIdcardservice;
	@Autowired
	EnrolmentService enrolmentservice;
	@Autowired
	BookService bookservice;
	@Autowired
	CourseService courseservice;
	
	
	@Autowired
	StudentBusiness stdBussiness;
	@Autowired
	StudentIDCardBusiness stdIdCardBusiness;
	@Autowired
	EnrolmentBusiness enrolmentBusiness;
	@Autowired
	BookBusiness bookBusiness;
	@Autowired
	CourseBusiness courseBusiness;
	
	public StudentController(StudentService studentService) {
		this.studentservice = studentService;
	}
	
//	student
	@PostMapping(value = "/students")
	public ResponseEntity<Void> saveStudent(@RequestBody StudentPayload payload) throws BaseException{
		stdBussiness.saveStudent(payload);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/students")
	public ResponseEntity<List<StudentListJson>> getAllStudents() throws BaseException{
		return ResponseEntity.ok(stdBussiness.getListStudent());	
	}
	
	@GetMapping(value = "/students/{id}")
	public ResponseEntity<StudentListJson> getStudentById(@PathVariable("id")long id) throws BaseException{
		return ResponseEntity.ok(stdBussiness.getStudentId(id));
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<StudentListJson> updateStudent(@PathVariable("id") long id,
			@RequestBody StudentPayload payload){
		Optional<Student> stdData = studentservice.findOptionalById(id);
		if(stdData.isPresent()) {
			stdBussiness.updateStudent(stdData.get().getId(), payload);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id){
		try {
			stdBussiness.deleteStudent(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			// TODO: handle exception
		}
	}
	
	
//	Card
	@GetMapping(value = "/students/card")
	public ResponseEntity<List<StudentIDCardListJson>> findAllStudentIDCards() throws BaseException{
		return ResponseEntity.ok(stdIdCardBusiness.getListudentIdCard());	
	}
	@GetMapping(value = "/students/{id}/card")
	public ResponseEntity<StudentIDCardListJson> getStudentIdCardByStudentId(@PathVariable("id") long id) throws StudentException{
		return ResponseEntity.ok(stdIdCardBusiness.getStudenIdCardByStudentId(id));
	}
	@PostMapping(value = "/students/card")
	public ResponseEntity<Void> saveStudentIdCard(@RequestBody StudentIDCardPayload payload) throws BaseException{
		stdIdCardBusiness.saveStudentIdCard(payload);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PutMapping("/students/{id}/card")
	public ResponseEntity<StudentIDCardListJson> updateStudent(@PathVariable("id") long id,
			@RequestBody StudentIDCardPayload payload){
		System.out.println(payload.toString());
		Optional<StudentIDCard> stdIDCardData = studentIdcardservice.findOptionalById(id);
		if(stdIDCardData.isPresent()) {
			stdIdCardBusiness.updateStudentIdCard(stdIDCardData.get().getId(), payload);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
//	Enrolment
	@GetMapping(value = "/students/{id}/enrolment")
	public ResponseEntity<EnrolmentListJson> getEnrolmentByStudentId(@PathVariable("id") long id) throws StudentException{
		return ResponseEntity.ok(enrolmentBusiness.getEnrolmentByStudenId(id));
	}
	@PostMapping(value = "/students/enrolment")
	public ResponseEntity<Void> saveEnrolment(@RequestBody EnrolmentPayload payload) throws BaseException{
		System.out.println(payload.toString());
		enrolmentBusiness.saveEnrolment(payload);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PutMapping("/students/{id}/enrolment")
	public ResponseEntity<EnrolmentListJson> updateEnrolment(@PathVariable("id") long id,
			@RequestBody EnrolmentPayload payload){
		System.out.println(payload.toString());
		Optional<Enrolment> enrolmentData = enrolmentservice.findOptionalById(id);
		if(enrolmentData.isPresent()) {
			enrolmentBusiness.updateEnrolment(enrolmentData.get().getId(), payload);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
//	Book
	@GetMapping(value = "/students/{id}/book")
	public ResponseEntity<BookListJson> getBookByStudentId(@PathVariable("id") long id) throws StudentException{
		return ResponseEntity.ok(bookBusiness.getBookByStudentId(id));
	}
	@PostMapping(value = "/students/book")
	public ResponseEntity<Void> saveBook(@RequestBody BookPayload payload) throws BaseException{
		System.out.println(payload.toString());
		bookBusiness.saveBook(payload);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PutMapping("/students/book/{id}")
	public ResponseEntity<BookListJson> updateBook(@PathVariable("id") long id,
			@RequestBody BookPayload payload){
		System.out.println(payload.toString());
		Optional<Book> bookData = bookservice.findOptionalById(id);
		if(bookData.isPresent()) {
			bookBusiness.updateBook(bookData.get().getId(), payload);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
//	Course
	@GetMapping(value = "/course/{id}")
	public ResponseEntity<CourseListJson> getCourseByStudentId(@PathVariable("id") long id) throws StudentException{
		return ResponseEntity.ok(courseBusiness.getCourseByStudentId(id));
	}
	@PostMapping(value = "/course")
	public ResponseEntity<Void> saveCourse(@RequestBody CoursePayload payload) throws BaseException{
		System.out.println(payload.toString());
		courseBusiness.saveCourse(payload);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PutMapping("/course/{id}")
	public ResponseEntity<CourseListJson> updateCourse(@PathVariable("id") long id,
			@RequestBody CoursePayload payload){
		System.out.println(payload.toString());
		Optional<Course> courseData = courseservice.findOptionalById(id);
		if(courseData.isPresent()) {
			courseBusiness.updateCourse(courseData.get().getId(), payload);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
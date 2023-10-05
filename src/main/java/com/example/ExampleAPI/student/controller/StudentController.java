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
import com.example.ExampleAPI.student.model.Student;
import com.example.ExampleAPI.student.payload.StudentPayload;
import com.example.ExampleAPI.student.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	StudentService studentservice;
	
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
	
	
	
	@GetMapping(value = "/student/{id}/card")
	public ResponseEntity<StudentIDCardListJson> getStudentIdCardByStudentId(@PathVariable("id") long id) throws StudentException{
		return ResponseEntity.ok(stdIdCardBusiness.getStudenIdCardByStudentId(id));
	}
	
	@GetMapping(value = "/student/{id}/enrolment")
	public ResponseEntity<EnrolmentListJson> getEnrolmentByStudentId(@PathVariable("id") long id) throws StudentException{
		return ResponseEntity.ok(enrolmentBusiness.getEnrolmentByStudenId(id));
	}
	
	@GetMapping(value = "/student/{id}/book")
	public ResponseEntity<BookListJson> getBookByStudentId(@PathVariable("id") long id) throws StudentException{
		return ResponseEntity.ok(bookBusiness.getBookByStudentId(id));
	}
	
	@GetMapping(value = "/course/{id}")
	public ResponseEntity<CourseListJson> getCourseByStudentId(@PathVariable("id") long id) throws StudentException{
		return ResponseEntity.ok(courseBusiness.getCourseByStudentId(id));
	}
	
	
}
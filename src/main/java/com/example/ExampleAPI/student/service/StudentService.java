 package com.example.ExampleAPI.student.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExampleAPI.student.model.Student;
import com.example.ExampleAPI.student.repository.StudentRepository;
@Service
public class StudentService implements IStudent {
	@Autowired
	StudentRepository studenRepository;
	
	public StudentService(StudentRepository studenRepository) {
		this.studenRepository = studenRepository;
	}

	@Override
	public List<Student> getAllStudents(){
		return studenRepository.findAll();
	}
	
	@Override
	public Student findById(long id){
		return studenRepository.findById(id);
	}
	
	
	@Override
	public Student findByEmail(String email) {
		// TODO Auto-generated method stub
		return studenRepository.findByEmail(email);
	}

	@Override
	public Student save(Student std) {
		// TODO Auto-generated method stub
		return studenRepository.save(std);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		studenRepository.deleteById(id);
	}
	
	public Optional<Student> findOptionalById(long id) {
		return studenRepository.findOptionalById(id);
	}
	
	
}

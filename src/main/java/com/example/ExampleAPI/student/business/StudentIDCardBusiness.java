package com.example.ExampleAPI.student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExampleAPI.student.json.StudentIDCardListJson;

import com.example.ExampleAPI.student.model.StudentIDCard;
import com.example.ExampleAPI.student.payload.StudentIDCardPayload;

import com.example.ExampleAPI.student.service.StudentIDCardService;

@Service
public class StudentIDCardBusiness {
	
	@Autowired
	StudentIDCardService studentIDCardService;

	public List<StudentIDCardListJson> getListudentIdCard() {
		// TODO Auto-generated method stub
		return StudentIDCardListJson.packJson(studentIDCardService.findAllStudentIDCards());
	}
	public StudentIDCardListJson getStudenIdCardByStudentId(long id) {
		return StudentIDCardListJson.packJson(studentIDCardService.findByStudentId(id));
		
	}
	
	public void saveStudentIdCard(StudentIDCardPayload std) {
		StudentIDCard studentIdCard = new StudentIDCard(
										std.getStudentId(),
										std.getCardNumber());
		studentIDCardService.save(studentIdCard);
	}
	
	public void updateStudentIdCard(long id, StudentIDCardPayload payload) {
		StudentIDCard studentIdCard = studentIDCardService.findById(id);
		studentIdCard.setCardNumber(payload.getCardNumber());
		studentIDCardService.save(studentIdCard);
	}
	
	

}

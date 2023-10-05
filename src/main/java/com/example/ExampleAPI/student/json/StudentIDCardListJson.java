package com.example.ExampleAPI.student.json;

import java.util.ArrayList;
import java.util.List;

import com.example.ExampleAPI.student.model.StudentIDCard;
import com.example.ExampleAPI.student.model.Student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentIDCardListJson {
	private long id;
	private Student student;
	private String cardNumber;
	
	public static StudentIDCardListJson packJson(StudentIDCard studentIdCard) {
		StudentIDCardListJson sIdlj = new StudentIDCardListJson();
		sIdlj.setId(studentIdCard.getId());
		sIdlj.setStudent(studentIdCard.getStudent());
		sIdlj.setCardNumber(studentIdCard.getCardNumber());
		
		return sIdlj;
		
	}
	public static List<StudentIDCardListJson> packJson(List<StudentIDCard> studentIdCards) {
		List<StudentIDCardListJson> studentIdCardListJson = new ArrayList<StudentIDCardListJson>();
		for(StudentIDCard studentIdCard : studentIdCards) {
			studentIdCardListJson.add(packJson(studentIdCard));
		}
		return studentIdCardListJson;
	}

}

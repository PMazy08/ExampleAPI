package com.example.ExampleAPI.student.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExampleAPI.student.json.EnrolmentListJson;

import com.example.ExampleAPI.student.service.EnrolmentService;

@Service
public class EnrolmentBusiness {
	
	@Autowired
	EnrolmentService enrolmentService;
	
	public EnrolmentListJson getEnrolmentByStudenId(long id) {
		return EnrolmentListJson.packJson(enrolmentService.findByStudentId(id));
	}

}

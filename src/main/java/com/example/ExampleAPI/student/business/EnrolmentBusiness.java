package com.example.ExampleAPI.student.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExampleAPI.student.json.EnrolmentListJson;
import com.example.ExampleAPI.student.model.Enrolment;

import com.example.ExampleAPI.student.payload.EnrolmentPayload;

import com.example.ExampleAPI.student.service.EnrolmentService;

@Service
public class EnrolmentBusiness {
	
	@Autowired
	EnrolmentService enrolmentService;
	
	public EnrolmentListJson getEnrolmentByStudenId(long id) {
		return EnrolmentListJson.packJson(enrolmentService.findByStudentId(id));
	}
	
	public void saveEnrolment(EnrolmentPayload elm) {
		Enrolment enroment = new Enrolment(
				elm.getStudentId(),
				elm.getCourseId());
		enrolmentService.save(enroment);
	}
	
	public void updateEnrolment(long id, EnrolmentPayload payload) {
		Enrolment enrolment = enrolmentService.findById(id);
		enrolment.setStudent(payload.getStudentId());
		enrolment.setCourse(payload.getCourseId());
		enrolmentService.save(enrolment);
	}

}

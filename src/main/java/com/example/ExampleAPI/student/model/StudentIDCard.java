package com.example.ExampleAPI.student.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "StudentIDCard")
@Table(
		name = "student_id_card", uniqueConstraints = {
				@UniqueConstraint(name = "student_card_number_unique",columnNames = "card_number") })
public class StudentIDCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
	@Fetch(FetchMode.JOIN)
	private Student student;
	
	@Column(name = "card_number", unique = true, nullable = false)
	private String cardNumber;
	
	public StudentIDCard() {
		;
	}

	public StudentIDCard(Student studentId, String cardNumber) {
		super();
		this.student = studentId;
		this.cardNumber = cardNumber;
	}

}

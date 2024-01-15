package com.example.student.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentDTO {
	private Long id;
	private String studentName;
	private String studentNumber;
	private String studentMobile;
}

package com.example.student.service;

import org.springframework.stereotype.Service;

import com.example.student.dto.StudentDTO;

@Service
public class StudentService {

	public void save(StudentDTO studentDTO) {
		System.out.println("StudentService.save()");
		System.out.println("StudentDTO = " + studentDTO);
		
	}

}

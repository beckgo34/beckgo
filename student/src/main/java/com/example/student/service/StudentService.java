package com.example.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.dto.StudentDTO;
import com.example.student.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	public void save(StudentDTO studentDTO) {
		System.out.println("StudentService.save()");
		System.out.println("StudentDTO = " + studentDTO);
		studentRepository.save(studentDTO);
	}

	public List<StudentDTO> findAll() {
		return studentRepository.finAll();
	}
	

}

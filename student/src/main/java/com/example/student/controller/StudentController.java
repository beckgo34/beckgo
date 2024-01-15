package com.example.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.student.dto.StudentDTO;
import com.example.student.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	// 학생등록 화면 출력
	@GetMapping("/sava")
	public String sava() {
		return "save";
	}
	
	// 학생등록 처리
	@PostMapping("/save")
	public String save(@ModelAttribute StudentDTO studentDTO) {
		System.out.println(studentDTO);
		// StudentService 클래스의 save 메서드로 StudentDTO 객체 전달
		studentService.save(studentDTO);
		return "index";
	}
}

package com.example.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.book.dto.BookDTO;
import com.example.book.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	// 책등록 페이지 출력
	@GetMapping("/save")
	public String save() {
		return "save";
	}
	
	// 책등록 처리
	@PostMapping("/save")
	public String save(@ModelAttribute BookDTO bookDTO) {
		bookService.save(bookDTO);
		return "index";
	}
	
	// 책 전체목록 조회
	@GetMapping("/list")
	public String findAll(Model model) {
		List<BookDTO> bookDTOs = bookService.findAll();
		model.addAttribute("bookList",bookDTOs);
		return "list";
	}
	
	@GetMapping("/book/{id}")
	public String findById(@PathVariable("id") Long id, Model model) {
		BookDTO bookDTO = bookService.findById(id);
		model.addAttribute("book",bookDTO);
		return "detail";
	}
}

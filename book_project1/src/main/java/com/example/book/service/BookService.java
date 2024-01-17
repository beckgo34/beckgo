package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDTO;
import com.example.book.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public void save(BookDTO bookDTO) {
		bookRepository.save(bookDTO);
	}

	public List<BookDTO> findAll() {
		return bookRepository.findAll();
	}

	public BookDTO findById(Long id) {
		return bookRepository.findById(id);
	}

	
}

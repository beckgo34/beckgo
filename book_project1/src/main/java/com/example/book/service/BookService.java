package com.example.book.service;

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
}

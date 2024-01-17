package com.example.book.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@Data
public class BookDTO {
	private Long id;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private int bookPrice;
}

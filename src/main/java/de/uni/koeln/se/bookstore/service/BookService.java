package de.uni.koeln.se.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import de.uni.koeln.se.bookstore.repository.BookRepo;
import de.uni.koeln.se.bookstore.datamodel.Book;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> findBooks() {
		
		return bookRepo.findAll();
		
	}
	
	public Optional<Book> fetchBook(int id) {
		
		return bookRepo.findById(id);
	}
	
	public Book addBook(Book book) {
		
		return bookRepo.save(book);
	}
	
	public boolean deleteBook(int id) {
		
		boolean status;
		try {
			bookRepo.deleteById(id);
		status=true;
		}catch (Exception e) {
			status=false; }
		return status;
	}
}


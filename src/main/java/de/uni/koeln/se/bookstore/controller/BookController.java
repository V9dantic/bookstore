package de.uni.koeln.se.bookstore.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni.koeln.se.bookstore.service.BookService;
import de.uni.koeln.se.bookstore.datamodel.*;

@RequestMapping("/bookStore")
@RestController
public class BookController {
	
	@Autowired
	BookService bookSer;

	@GetMapping
	public ResponseEntity<List<Book>> getAllbooks() {
		
		List<Book> books = new ArrayList<Book> ();
		books=bookSer.findBooks();
		
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@GetMapping("/{id}" )
	public ResponseEntity<Book> getBookId(@PathVariable int id) {
		
		return new ResponseEntity<>(bookSer.fetchBook(id).get(), HttpStatus.OK);
	
	}
	
	@PostMapping
	public ResponseEntity<Book> addBookt(@RequestBody Book book) {
		
		bookSer.addBook(book);
		
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}" )
	public ResponseEntity<Book> removeBookById(@PathVariable int id) {
		Book book = bookSer.fetchBook(id).get();
		
		if(bookSer.deleteBook(id) ) {
			return new ResponseEntity<>(book , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/new")
	public ResponseEntity<Book> getRecentBook() {

		List<Book> books = bookSer.findBooks();
		sortYears sY = new sortYears();

		Collections.sort(books, sY);
		
		return new ResponseEntity<>(books.get(0), HttpStatus.OK);
	
	}

	@GetMapping("/old")
	public ResponseEntity<Book> getOldestBook() {

		List<Book> books = bookSer.findBooks();
		sortYears sY = new sortYears();

		Collections.sort(books, sY);
		
		return new ResponseEntity<>(books.get(books.size()-1), HttpStatus.OK);
	
	}
}

class sortYears implements Comparator<Book> {
	public int compare(Book a, Book b) {

		if(a.getYearPublished() < b.getYearPublished()) {
			return 1;
		} else if (a.getYearPublished() == b.getYearPublished()) {
			return 0;
		} else{
			return -1;
		}	
	}
}

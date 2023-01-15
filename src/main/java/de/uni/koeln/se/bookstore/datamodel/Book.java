package de.uni.koeln.se.bookstore.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	private String name;
	private String author;
	private Integer yearPublished;
	private Double price;

	public Book() {}
	
	public Book(String name, String author, Integer yearPublished, Double price) {
		
		this.name = name;
		this.author = author;
		this.yearPublished = yearPublished;
		setPrice(price);
	}

	//	Getter and Setters
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setYearPublished(Integer yearPublished) {
		this.yearPublished = yearPublished;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public Integer getYearPublished() {
		return this.yearPublished;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}

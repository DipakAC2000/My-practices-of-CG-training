package com.springboot.demo;

class Author{
	private String name;
	private String email;
	
	public Author() {
	}
	
	public Author(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

class Book{
	private String isbn;
	private String name;
	private Author author[];
	private double price;
	private int qty = 0;
	
	public Book(String isbn, String name, Author[] author, double price, int qty) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.price = price;
		this.qty = qty;
	}
	public Book(String isbn, String name, Author[] author, double price) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.price = price;
		
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Author[] getAuthor() {
		return author;
	}
	public void setAuthor(Author[] author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public void printBook() {
		System.out.println(this.isbn);
		System.out.println(this.name);
		for(Author auth : this.author) {
			System.out.println(auth.getName());
			System.out.println(auth.getEmail());
		}
		System.out.println(this.price);
		System.out.println(this.qty);
	}

}

public class Demo {

	public static void main(String[] args) {
		
	}
}

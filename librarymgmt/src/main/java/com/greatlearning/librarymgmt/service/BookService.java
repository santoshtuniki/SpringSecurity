package com.greatlearning.librarymgmt.service;

import java.util.List;

import com.greatlearning.librarymgmt.model.Book;

public interface BookService {

	public List<Book> getAllBooks();

	public Book getBookById(int id);

	public void save(Book book);

	public void deleteBookById(int id);

	public List<Book> search(String author, String name);
}

package com.greatlearning.librarymgmt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.librarymgmt.model.Book;
import com.greatlearning.librarymgmt.repository.BookRepository;
import com.greatlearning.librarymgmt.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBookById(int id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public void save(Book book) {
		bookRepository.save(book);
		bookRepository.flush();
	}

	@Override
	public void deleteBookById(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> search(String author, String name) {
		return bookRepository.findByNameContainsAndAuthorContainsAllIgnoreCase(name, author);
	}
}

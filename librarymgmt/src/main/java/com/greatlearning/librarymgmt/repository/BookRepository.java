package com.greatlearning.librarymgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.librarymgmt.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	public List<Book> findByNameContainsAndAuthorContainsAllIgnoreCase(String name, String author);
}

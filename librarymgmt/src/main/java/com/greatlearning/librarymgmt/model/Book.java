package com.greatlearning.librarymgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "author")
	private String author;

	@Column(name = "category")
	private String category;

	public Book() {

	}

	public Book(String name, String category, String author) {
		super();
		this.name = name;
		this.category = category;
		this.author = author;
	}
}

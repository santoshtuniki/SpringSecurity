package com.greatlearning.librarymgmt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.librarymgmt.model.Book;
import com.greatlearning.librarymgmt.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	@RequestMapping("/list")
	public String getAllBooks(Model model) {
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("Books", books);
		return "list-books";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Book book = new Book();
		model.addAttribute("Book", book);
		return "Book-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(Model model, @RequestParam(name = "bookId") int id) {
		Book book = bookService.getBookById(id);
		model.addAttribute("Book", book);
		return "Book-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "bookId") int id) {
		bookService.deleteBookById(id);
		return "redirect:/books/list";
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name,
			@RequestParam(name = "author") String author, @RequestParam(name = "category") String category) {
		System.out.println(id);
		Book book;
		if (id != 0) {
			book = bookService.getBookById(id);
			book.setName(name);
			book.setAuthor(author);
			book.setCategory(category);
		} else {
			book = new Book(name, category, author);
		}
		bookService.save(book);
		return "redirect:/books/list";
	}

	@RequestMapping("/search")
	public String search(@RequestParam(name = "name") String name, @RequestParam(name = "author") String author,
			Model model) {
		if (StringUtils.isEmpty(author) && StringUtils.isEmpty(name)) {
			return "redirect:/books/list";
		} else {
			List<Book> books = bookService.search(author, name);
			model.addAttribute("Books", books);
			return "list-books";
		}
	}

	@GetMapping("/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}
}

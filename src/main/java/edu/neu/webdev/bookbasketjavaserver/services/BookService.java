package edu.neu.webdev.bookbasketjavaserver.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.webdev.bookbasketjavaserver.models.Book;
import edu.neu.webdev.bookbasketjavaserver.repositories.BookRepository;

@RestController
@CrossOrigin(origins = "*")
public class BookService {

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/api/book")
	public Iterable<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@PostMapping("/api/book")
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@DeleteMapping("/api/book/{bookId}")
	public void deleteBook(@PathVariable("bookId") int id) {
		bookRepository.deleteById(id);
	}

	@GetMapping("/api/book/{bookId}")
	public Book findBookById(@PathVariable("bookId") int id) {
		Optional<Book> data = bookRepository.findById(id);
		if (data.isPresent())
			return data.get();
		return null;

	}

}

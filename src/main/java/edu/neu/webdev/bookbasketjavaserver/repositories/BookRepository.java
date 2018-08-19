package edu.neu.webdev.bookbasketjavaserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.neu.webdev.bookbasketjavaserver.models.Book;
import edu.neu.webdev.bookbasketjavaserver.models.Note;


public interface BookRepository extends CrudRepository<Book, Integer>{

	
	@Query("SELECT b FROM Book b WHERE b.isbn10=:isbn10")
	Optional<Book> findBookWithISBN(@Param("isbn10") String isbn10);
}

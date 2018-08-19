package edu.neu.webdev.bookbasketjavaserver.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.neu.webdev.bookbasketjavaserver.models.Book;
import edu.neu.webdev.bookbasketjavaserver.models.Note;
import edu.neu.webdev.bookbasketjavaserver.models.User;


public interface NoteRepository extends CrudRepository<Note, Integer>{
	
	@Query("SELECT n FROM Note n WHERE n.user=:user and n.book=:book")
	Iterable<Note> findNoteByUserBook(@Param("user") User user, @Param("book") Book book);

	@Query("SELECT n FROM Note n WHERE n.book=:book")
	Iterable<Note> findAllNotesForBook(@Param("book") Book book);

}

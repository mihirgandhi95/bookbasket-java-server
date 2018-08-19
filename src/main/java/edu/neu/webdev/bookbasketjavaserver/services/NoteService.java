package edu.neu.webdev.bookbasketjavaserver.services;

import java.util.List;
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
import edu.neu.webdev.bookbasketjavaserver.models.Note;
import edu.neu.webdev.bookbasketjavaserver.models.Review;
import edu.neu.webdev.bookbasketjavaserver.models.User;
import edu.neu.webdev.bookbasketjavaserver.repositories.BookRepository;
import edu.neu.webdev.bookbasketjavaserver.repositories.NoteRepository;
import edu.neu.webdev.bookbasketjavaserver.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class NoteService {

	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
	
	
	
	
	/**
	 * Create a new entry for a review given by a particular critic for a particular song
	 * @param criticId
	 * @param songId
	 * @param review
	 * @return
	 */
	@PostMapping("/api/note/user/{userId}/book/{bookId}")
	public  Note createNote(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId, @RequestBody Note note) {
		User user = userService.findUserById(userId);
		Book book = bookService.findBookById(bookId);
		note.setUser(user);
		note.setBook(book);
		note = noteRepository.save(note);
		book.getNotes().add(note);
		bookRepository.save(book);
		userRepository.save(user);
		return note;
	}
	
	
	/**
	 * Retrieve the list of reviews given by a critic for a particular book
	 * @param userId
	 * @param bookId
	 * @return
	 */
	@GetMapping("/api/note/user/{userId}/book/{bookId}")
	public List<Note> findNoteByBookUser(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId) {		
		return (List<Note>) noteRepository.findNoteByUserBook(userService.findUserById(userId), bookService.findBookById(bookId));
	}
	
	/**
	 * Retrieves the list of reviews for a particular book
	 * @param bookId
	 * @return
	 */
	@GetMapping("/api/note/book/{bookId}")
	public List<Note> findAllNotesForBook(@PathVariable("bookId") int bookId){
		return (List<Note>) noteRepository.findAllNotesForBook(bookService.findBookById(bookId));
	}


	@GetMapping("/api/note")
	public Iterable<Note> findAllNotes() {
		return noteRepository.findAll();
	}

	@PostMapping("/api/note")
	public Note createNote(@RequestBody Note note) {
		return noteRepository.save(note);
	}

	@DeleteMapping("/api/note/{noteId}")
	public void deleteNote(@PathVariable("noteId") int id) {
		noteRepository.deleteById(id);
	}

	@GetMapping("/api/note/{noteId}")
	public Note findNoteById(@PathVariable("noteId") int id) {
		Optional<Note> data = noteRepository.findById(id);
		if (data.isPresent())
			return data.get();
		return null;

	}

}

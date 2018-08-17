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

import edu.neu.webdev.bookbasketjavaserver.models.Note;
import edu.neu.webdev.bookbasketjavaserver.repositories.NoteRepository;

@RestController
@CrossOrigin(origins = "*")
public class NoteService {

	@Autowired
	NoteRepository noteRepository;

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

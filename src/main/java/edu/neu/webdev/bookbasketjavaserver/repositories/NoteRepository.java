package edu.neu.webdev.bookbasketjavaserver.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.neu.webdev.bookbasketjavaserver.models.Note;


public interface NoteRepository extends CrudRepository<Note, Integer>{

}

package edu.neu.webdev.bookbasketjavaserver.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.neu.webdev.bookbasketjavaserver.models.Book;


public interface BookRepository extends CrudRepository<Book, Integer>{

}

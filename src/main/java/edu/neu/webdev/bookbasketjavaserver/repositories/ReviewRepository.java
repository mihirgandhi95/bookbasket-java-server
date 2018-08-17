package edu.neu.webdev.bookbasketjavaserver.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.neu.webdev.bookbasketjavaserver.models.Review;


public interface ReviewRepository extends CrudRepository<Review, Integer>{

}

package edu.neu.webdev.bookbasketjavaserver.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.neu.webdev.bookbasketjavaserver.models.Book;
import edu.neu.webdev.bookbasketjavaserver.models.Note;
import edu.neu.webdev.bookbasketjavaserver.models.Review;
import edu.neu.webdev.bookbasketjavaserver.models.User;


public interface ReviewRepository extends CrudRepository<Review, Integer>{

	
	@Query("SELECT n FROM Review n WHERE n.user=:user and n.book=:book")
	Iterable<Review> findReviewByUserBook(@Param("user") User user, @Param("book") Book book);

	@Query("SELECT n FROM Review n WHERE n.book=:book")
	Iterable<Review> findAllReviewsForBook(@Param("book") Book book);

}

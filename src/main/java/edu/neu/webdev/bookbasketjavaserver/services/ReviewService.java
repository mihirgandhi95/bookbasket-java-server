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
import edu.neu.webdev.bookbasketjavaserver.repositories.ReviewRepository;
import edu.neu.webdev.bookbasketjavaserver.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	
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
	@PostMapping("/api/review/user/{userId}/book/{bookId}")
	public Review createReview(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId, @RequestBody Review review) {
		User user = userService.findUserById(userId);
		Book book = bookService.findBookById(bookId);
		review.setUser(user);
		review.setBook(book);
		review = reviewRepository.save(review);
		book.getReviews().add(review);
		bookRepository.save(book);
		userRepository.save(user);
		return review;
	}
	
	
	/**
	 * Retrieve the list of reviews given by a critic for a particular book
	 * @param userId
	 * @param bookId
	 * @return
	 */
	@GetMapping("/api/review/user/{userId}/book/{bookId}")
	public List<Review> findReviewByBookUser(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId) {		
		return (List<Review>) reviewRepository.findReviewByUserBook(userService.findUserById(userId), bookService.findBookById(bookId));
	}
	
	/**
	 * Retrieves the list of reviews for a particular book
	 * @param bookId
	 * @return
	 */
	@GetMapping("/api/review/book/{bookId}")
	public List<Review> findAllReviewsForBook(@PathVariable("bookId") int bookId){
		return (List<Review>) reviewRepository.findAllReviewsForBook(bookService.findBookById(bookId));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	@GetMapping("/api/review")
	public Iterable<Review> findAllReviews() {
		return reviewRepository.findAll();
	}

	@PostMapping("/api/review")
	public Review createReview(@RequestBody Review review) {
		return reviewRepository.save(review);
	}

	@DeleteMapping("/api/review/{reviewId}")
	public void deleteReview(@PathVariable("reviewId") int id) {
		reviewRepository.deleteById(id);
	}

	@GetMapping("/api/review/{reviewId}")
	public Review findReviewById(@PathVariable("reviewId") int id) {
		Optional<Review> data = reviewRepository.findById(id);
		if (data.isPresent())
			return data.get();
		return null;

	}

}

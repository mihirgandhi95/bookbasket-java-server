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

import edu.neu.webdev.bookbasketjavaserver.models.Review;
import edu.neu.webdev.bookbasketjavaserver.repositories.ReviewRepository;

@RestController
@CrossOrigin(origins = "*")
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

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

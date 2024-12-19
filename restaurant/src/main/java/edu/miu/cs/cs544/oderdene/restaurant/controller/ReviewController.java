package edu.miu.cs.cs544.oderdene.restaurant.controller;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Review;
import edu.miu.cs.cs544.oderdene.restaurant.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    // GET - Get all reviews by a customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getReviewsByCustomer(@PathVariable Integer customerId) {
        return new ResponseEntity<>(reviewService.getReviewsByCustomerId(customerId), HttpStatus.OK);
    }

    // GET - Get all reviews for a restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Review>> getReviewsByRestaurant(@PathVariable Integer restaurantId) {
        return new ResponseEntity<>(reviewService.getReviewsByRestaurantId(restaurantId), HttpStatus.OK);
    }

    // POST - Add a new review
    @PostMapping
    public ResponseEntity<Review> addReview(
            @RequestParam Integer customerId,
            @RequestParam Integer restaurantId,
            @RequestBody Review review) {
        return new ResponseEntity<>(reviewService.addReview(customerId, restaurantId, review), HttpStatus.CREATED);
    }

    // DELETE - Delete a review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

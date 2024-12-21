package edu.miu.cs.cs544.oderdene.restaurant.service;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Customer;
import edu.miu.cs.cs544.oderdene.restaurant.entity.Restaurant;
import edu.miu.cs.cs544.oderdene.restaurant.entity.Review;
import edu.miu.cs.cs544.oderdene.restaurant.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.oderdene.restaurant.jms.Sender;
import edu.miu.cs.cs544.oderdene.restaurant.repository.CustomerRepository;
import edu.miu.cs.cs544.oderdene.restaurant.repository.RestaurantRepository;
import edu.miu.cs.cs544.oderdene.restaurant.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private Sender sender;

    @Autowired
    private CustomerService customerService;

    public List<Review> getReviewsByCustomerId(Integer customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    public List<Review> getReviewsByRestaurantId(Integer restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId);
    }

    public Review addReview(Integer restaurantId, Review review) {
        Customer currentCust = customerService.getCurrentUser();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        boolean reviewExists = reviewRepository.existsByCustomerAndRestaurant(currentCust, restaurant);
        if (reviewExists) {
            throw new ResourceNotFoundException("You have already reviewed this restaurant.");
        }

        review.setCustomer(currentCust);
        review.setRestaurant(restaurant);

        // send rating
        sender.sendRating(restaurantId, review.getRating());

        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Integer reviewId) {
        try {
            Customer currentCust = customerService.getCurrentUser();
            Review review = reviewRepository.findByIdAndCustomerId(reviewId, currentCust.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
            reviewRepository.delete(review);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete review with pessimistic lock: " + ex.getMessage());
        }
    }
}
